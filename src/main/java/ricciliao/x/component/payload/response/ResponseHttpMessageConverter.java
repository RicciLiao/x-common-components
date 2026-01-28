package ricciliao.x.component.payload.response;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;
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
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.ResponseCodeEnum;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.io.IOException;
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
    protected void writeInternal(@Nonnull Response<PayloadData> response, Type type, @Nonnull HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        ResponseCode code;
        PayloadData data;
        if (Objects.isNull(response.getCode())) {
            code = ResponseCodeEnum.UNEXPECTED_ERROR;
            data = SimplePayloadData.blank();
        } else {
            code = response.getCode();
            if (Objects.isNull(response.getData())) {
                data = SimplePayloadData.blank();
            } else {
                data = response.getData();
            }
        }

        ObjectNode codeNode = objectMapper.createObjectNode();
        SecondaryCode secondary = code.isSecondaryBlank() ? SecondaryCodeEnum.BLANK : code.getSecondary();
        String id = String.format("%d%03d", code.getPrimary().getId(), secondary.getId());
        String message = StringUtils.isBlank(secondary.getMessage()) ? code.getPrimary().getMessage() : secondary.getMessage();
        codeNode.put("id", id);
        codeNode.put("message", message);

        ObjectNode responseNode = objectMapper.createObjectNode();
        responseNode.set("code", codeNode);
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

            return ResponseUtils.unexpected();
        }
        //code
        ResponseCode code;
        String id = response.get("code").get("id").asText();
        String message = response.get("code").get("message").asText();
        int pcId = Integer.parseInt(id.substring(0, 1));
        int scId = Integer.parseInt(id.substring(1));
        if (scId == 0) {
            code = ResponseCode.of(PrimaryCode.of(pcId, message), SecondaryCodeEnum.BLANK);
        } else {
            code = ResponseCode.of(PrimaryCode.of(pcId, ""), SecondaryCode.of(scId, message));
        }
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
                && jsonNode.get("code").get("id").asText().length() == 4;
    }

    private JavaType getJavaType(Type type, Class<?> contextClass) {

        return this.objectMapper.constructType(GenericTypeResolver.resolveType(type, contextClass));
    }
}
