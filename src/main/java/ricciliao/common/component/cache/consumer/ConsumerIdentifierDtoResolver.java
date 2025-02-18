package ricciliao.common.component.cache.consumer;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ricciliao.common.component.cache.CacheConstants;
import ricciliao.common.component.cache.pojo.ConsumerIdentifierDto;

public class ConsumerIdentifierDtoResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(ConsumerIdentifier.class)
                && parameter.getParameterType().isAssignableFrom(ConsumerIdentifierDto.class);
    }

    @Override
    public ConsumerIdentifierDto resolveArgument(@NonNull MethodParameter parameter,
                                                 ModelAndViewContainer mavContainer,
                                                 NativeWebRequest webRequest,
                                                 WebDataBinderFactory binderFactory) {

        return new ConsumerIdentifierDto(
                webRequest.getHeader(CacheConstants.HTTP_HEADER_FOR_CACHE_CUSTOMER),
                webRequest.getHeader(CacheConstants.HTTP_HEADER_FOR_CACHE_STORE)
        );
    }

}
