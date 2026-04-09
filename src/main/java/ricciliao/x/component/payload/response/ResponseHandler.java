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
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.lang.reflect.Type;
import java.util.Objects;

public class ResponseHandler implements HandlerMethodReturnValueHandler {

    private final ResponseHttpMessageConverter converter;

    public ResponseHandler(ResponseHttpMessageConverter converter) {
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
            response = Response.of(ResponseCode.of(PrimaryCodeEnum.SUCCESS, SecondaryCodeEnum.BLANK), SimplePayloadData.blank());
        } else if (returnValue instanceof Response<?> r) {
            response = Response.of(r.getCode(), r.getData());
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
