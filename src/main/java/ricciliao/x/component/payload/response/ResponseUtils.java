package ricciliao.x.component.payload.response;


import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.impl.ResponseCodeEnum;
import ricciliao.x.component.utils.CoreUtils;

import java.util.Objects;
import java.util.Optional;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Response<PayloadData> success() {

        return Response.of(ResponseCodeEnum.SUCCESS, SimplePayloadData.blank());
    }

    public static <T extends PayloadData> Response<T> success(T data) {

        return Response.of(ResponseCodeEnum.SUCCESS, data);
    }

    public static Response<PayloadData> unexpected() {

        return Response.of(ResponseCodeEnum.UNEXPECTED_ERROR, SimplePayloadData.blank());
    }

    public static Response<PayloadData> bindingResult(ResponseCode code, BindingResult bindingResult) {

        return Response.of(code, SimplePayloadData.of(CoreUtils.toFieldViolation(bindingResult)));
    }

    public static boolean isBlankResponse(Response<?> response) {

        return Objects.isNull(response) || response.isBlankData();
    }

    @Nullable
    public static <T extends PayloadData> T safetyGetResponseData(ResponseEntity<Response<T>> entity) {

        return Optional.ofNullable(safetyGetResponse(entity))
                .filter(tResponse -> !ResponseUtils.isBlankResponse(tResponse))
                .map(Response::getData)
                .orElse(null);
    }

    @Nullable
    public static <T extends PayloadData> Response<T> safetyGetResponse(ResponseEntity<Response<T>> entity) {

        return Optional.ofNullable(entity)
                .filter(e -> HttpStatus.OK.equals(e.getStatusCode()))
                .map(ResponseEntity::getBody)
                .orElse(null);
    }

}
