package ricciliao.x.component.payload.response;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SimpleResponseCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

public class ResponseHttpMessageConverter extends AbstractGenericHttpMessageConverter<Response<PayloadData>> {

    private final ObjectMapper objectMapper;

    public ResponseHttpMessageConverter(ObjectMapper objectMapper) {
        super(MediaType.APPLICATION_JSON);
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(@Nonnull Class<?> clazz) {

        return Response.class.isAssignableFrom(clazz);
    }

    @Override
    public boolean canRead(@Nonnull Type type, @Nullable Class<?> contextClass, @Nullable MediaType mediaType) {
        if (type instanceof ParameterizedType pType) {
            if (pType.getRawType().equals(Response.class)) {

                return super.canRead(type, contextClass, mediaType);
            } else {

                return false;
            }
        }

        return super.canRead(type, contextClass, mediaType);
    }

    @Override
    protected void writeInternal(@Nonnull Response<PayloadData> response, Type type, @Nonnull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ResponseCode code;
        PayloadData data;
        if (Objects.isNull(response.getCode())) {
            code = ResponseCode.of(PrimaryCodeEnum.UNEXPECTED_ERROR, SecondaryCodeEnum.BLANK);
            data = SimplePayloadData.blank();
        } else {
            code = response.getCode();
            if (Objects.isNull(response.getData())) {
                data = SimplePayloadData.blank();
            } else {
                data = response.getData();
            }
        }

        ObjectNode responseNode = objectMapper.createObjectNode();
        responseNode.putPOJO("code", ResponseUtils.convert2SimpleResponseCode(code));
        responseNode.set("data", objectMapper.valueToTree(data));
        objectMapper.writeValue(outputMessage.getBody(), responseNode);
    }

    @Nonnull
    @Override
    protected Response<PayloadData> readInternal(@Nonnull Class<? extends Response<PayloadData>> clazz,
                                                 @Nonnull HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        return this.readResponse(this.getJavaType(clazz, null), inputMessage);
    }

    @Nonnull
    @Override
    public Response<PayloadData> read(@Nonnull Type type, Class<?> contextClass, @Nonnull HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        return this.readResponse(this.getJavaType(type, contextClass), inputMessage);
    }

    private Response<PayloadData> readResponse(JavaType javaType, HttpInputMessage inputMessage) throws IOException {
        JsonNode response = objectMapper.readTree(inputMessage.getBody());
        if (!this.isValidatedResponse(response)) {

            throw new HttpMessageNotReadableException("Response code is invalid.", inputMessage);
        }
        //code
        SimpleResponseCode simpleResponseCode = objectMapper.convertValue(response.get("code"), SimpleResponseCode.class);
        ResponseCode code = ResponseUtils.convert2ResponseCode(simpleResponseCode);
        //data
        PayloadData data;
        if (!response.hasNonNull("data")
            || response.get("data").isNull()
            || (response.get("data").isContainerNode() && response.get("data").isEmpty())) {
            data = SimplePayloadData.blank();
        } else {
            data = objectMapper.treeToValue(response.get("data"), javaType.containedType(0));
        }

        return Response.of(code, data);
    }

    private boolean isValidatedResponse(JsonNode jsonNode) {

        return !jsonNode.isNull()
               && !jsonNode.isEmpty()
               && jsonNode.hasNonNull("code")
               && jsonNode.get("code").hasNonNull("id")
               && NumberUtils.isDigits(jsonNode.get("code").get("id").asText())
               && jsonNode.get("code").get("id").asText().length() == 5;
    }

    private JavaType getJavaType(Type type, Class<?> contextClass) {

        return this.objectMapper.constructType(GenericTypeResolver.resolveType(type, contextClass));
    }
}