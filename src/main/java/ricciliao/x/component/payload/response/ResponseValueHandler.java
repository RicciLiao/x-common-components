package ricciliao.x.component.payload.response;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimpleData;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.io.Serial;
import java.lang.reflect.Type;
import java.util.Objects;

public class ResponseValueHandler implements HandlerMethodReturnValueHandler {

    private final ResponseCode unkownResponseCode;
    private final ResponseHttpMessageConverter converter;

    public ResponseValueHandler(ResponseHttpMessageConverter converter) {
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
        this.converter = converter;
    }


    @Override
    public boolean supportsReturnType(@Nonnull MethodParameter returnType) {

        return returnType.getParameterType().isAssignableFrom(Response.class);
    }

    @Override
    public void handleReturnValue(Object returnValue,
                                  @Nonnull MethodParameter returnType,
                                  @Nonnull ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {

        HttpServletResponse servletResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        if (Objects.isNull(servletResponse)) {
            mavContainer.setRequestHandled(false);

            return;
        }
        Response<PayloadData> response;
        if (Objects.isNull(returnValue)) {
            response = Response.of(unkownResponseCode, SimpleData.blank());
        } else if (returnValue instanceof Response) {
            response = Response.of(((Response<?>) returnValue).getCode(), ((Response<?>) returnValue).getData());
        } else {
            mavContainer.setRequestHandled(false);

            return;
        }
        mavContainer.setRequestHandled(true);
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(servletResponse);
        Type type = GenericTypeResolver.resolveType(returnType.getGenericParameterType(), returnType.getContainingClass());
        converter.write(response, type, MediaType.APPLICATION_JSON, httpResponse);
    }
}
