package ricciliao.x.component.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.response.Response;

public class ResponseVoReferenceUtils {

    private ResponseVoReferenceUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends PayloadData> ParameterizedTypeReference<Response<T>> withGenerics(Class<T> generic) {

        return ParameterizedTypeReference.forType(
                ResolvableType.forClassWithGenerics(
                        Response.class,
                        generic
                ).getType()
        );
    }

    public static <T extends PayloadData> ParameterizedTypeReference<Response<T>> withGenerics(Class<?>... generics) {
        ResolvableType currentType = ResolvableType.forClass(generics[generics.length - 1]);
        for (int i = generics.length - 2; i >= 0; i--) {
            currentType = ResolvableType.forClassWithGenerics(generics[i], currentType);
        }

        return ParameterizedTypeReference.forType(ResolvableType.forClassWithGenerics(Response.class, currentType).getType());
    }

}
