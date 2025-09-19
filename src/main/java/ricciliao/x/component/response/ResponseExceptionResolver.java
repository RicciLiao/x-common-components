package ricciliao.x.component.response;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.sneaky.SneakyThrowUtils;
import ricciliao.x.log.AuditLoggerFactory;
import ricciliao.x.log.logger.AuditLogger;

import java.lang.reflect.Type;
import java.util.Objects;

public class ResponseExceptionResolver extends AbstractHandlerMethodExceptionResolver {

    private final static AuditLogger auditLogger = AuditLoggerFactory.getLogger(ResponseExceptionResolver.class);
    private final ResponseHttpMessageConverter converter;

    public ResponseExceptionResolver(ResponseHttpMessageConverter converter) {
        this.converter = converter;
    }

    @Override
    public ModelAndView doResolveHandlerMethodException(@Nonnull HttpServletRequest request,
                                                        @Nonnull HttpServletResponse response,
                                                        @Nullable HandlerMethod handler,
                                                        @Nonnull Exception ex) {
        Response<ResponseData> xResponse;
        if (ex instanceof AbstractException aex) {
            xResponse = Response.of(ResponseCode.of(aex.getPrimaryCode(), aex.getSecondaryCode()), aex.getResponseData());
        } else {
            xResponse = ResponseUtils.unexpected();
        }
        Type type = Objects.nonNull(handler) ?
                GenericTypeResolver.resolveType(handler.getReturnType().getGenericParameterType(), handler.getReturnType().getContainingClass()) :
                null;
        SneakyThrowUtils.run(() -> converter.write(xResponse, type, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response)));
        auditLogger.error(ex.getMessage(), ex);

        return new ModelAndView();
    }

}
