package ricciliao.x.component.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import ricciliao.x.component.response.Response;
import ricciliao.x.component.response.data.ResponseData;

public class ResponseVoReferenceUtils {

    private ResponseVoReferenceUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends ResponseData> ParameterizedTypeReference<Response<T>> withGenerics(Class<T> generic) {

        return ParameterizedTypeReference.forType(
                ResolvableType.forClassWithGenerics(
                        Response.class,
                        generic
                ).getType()
        );
    }

    public static <T extends ResponseData> ParameterizedTypeReference<Response<T>> forClassWithGenerics(Class<?> rawClass, Class<?>... generics) {

        return ParameterizedTypeReference.forType(
                ResolvableType.forClassWithGenerics(
                        Response.class,
                        ResolvableType.forClassWithGenerics(rawClass, generics)
                ).getType()
        );
    }

}
