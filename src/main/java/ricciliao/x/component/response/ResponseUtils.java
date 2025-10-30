package ricciliao.x.component.response;


import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.code.impl.ResponseCodeEnum;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;
import ricciliao.x.component.utils.CoreUtils;

import java.util.Optional;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Response<ResponseData> success() {

        return Response.of(ResponseCodeEnum.SUCCESS, SimpleData.blank());
    }

    public static <T extends ResponseData> Response<T> success(T data) {

        return Response.of(ResponseCodeEnum.SUCCESS, data);
    }

    public static Response<ResponseData> unexpected() {

        return Response.of(ResponseCodeEnum.UNEXPECTED_ERROR, SimpleData.blank());
    }

    public static Response<ResponseData> bindingResult(ResponseCode code, BindingResult bindingResult) {

        return Response.of(code, SimpleData.of(CoreUtils.toFieldViolation(bindingResult)));
    }

    public static boolean isBlankResponse(Response<?> response) {

        return Optional.ofNullable(response)
                .map(Response::isBlankData)
                .isEmpty();
    }

    @Nullable
    public static <T extends ResponseData> T safetyGetResponseData(ResponseEntity<Response<T>> entity) {

        return Optional.ofNullable(safetyGetResponse(entity))
                .filter(ResponseUtils::isBlankResponse)
                .map(Response::getData)
                .orElse(null);
    }

    @Nullable
    public static <T extends ResponseData> Response<T> safetyGetResponse(ResponseEntity<Response<T>> entity) {

        return Optional.ofNullable(entity)
                .filter(e -> HttpStatus.OK.equals(e.getStatusCode()))
                .map(ResponseEntity::getBody)
                .orElse(null);
    }

}
