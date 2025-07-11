package ricciliao.x.component.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import ricciliao.x.component.response.ResponseData;
import ricciliao.x.component.response.ResponseVo;

public class ResponseVoReferenceUtils {

    private ResponseVoReferenceUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends ResponseData> ParameterizedTypeReference<ResponseVo<T>> withGenerics(Class<T> generic) {

        return ParameterizedTypeReference.forType(
                ResolvableType.forClassWithGenerics(
                        ResponseVo.class,
                        generic
                ).getType()
        );
    }

    public static <T extends ResponseData> ParameterizedTypeReference<ResponseVo<T>> forClassWithGenerics(Class<?> rawClass, Class<?>... generics) {

        return ParameterizedTypeReference.forType(
                ResolvableType.forClassWithGenerics(
                        ResponseVo.class,
                        ResolvableType.forClassWithGenerics(rawClass, generics)
                ).getType()
        );
    }

}
