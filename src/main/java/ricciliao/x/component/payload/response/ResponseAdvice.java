package ricciliao.x.component.payload.response;


import jakarta.annotation.Nonnull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimpleData;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.io.Serial;
import java.util.Objects;

public class ResponseAdvice implements ResponseBodyAdvice<Response<? extends PayloadData>> {

    private final ResponseCode unkownResponseCode;

    public ResponseAdvice() {
        this.unkownResponseCode = new ResponseCode() {
            @Serial
            private static final long serialVersionUID = 5384926569899776189L;

            @Override
            public PrimaryCode getPrimary() {

                return PrimaryCodeEnum.UNEXPECTED_ERROR;
            }

            @Override
            public SecondaryCode getSecondary() {

                return SecondaryCodeEnum.BLANK;
            }
        };
    }

    @Override
    public boolean supports(@Nonnull MethodParameter returnType,
                            @Nonnull Class<? extends HttpMessageConverter<?>> converterType) {

        return returnType.getParameterType().isAssignableFrom(Response.class);
    }

    @Override
    public Response<? extends PayloadData> beforeBodyWrite(Response<? extends PayloadData> body,
                                                           @Nonnull MethodParameter returnType,
                                                           @Nonnull MediaType selectedContentType,
                                                           @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                                           @Nonnull ServerHttpRequest request,
                                                           @Nonnull ServerHttpResponse response) {
        if (Objects.isNull(body)) {

            return Response.of(unkownResponseCode, SimpleData.blank());
        }

        return body;
    }

}
