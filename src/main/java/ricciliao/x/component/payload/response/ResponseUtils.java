package ricciliao.x.component.payload.response;


import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.SimpleResponseCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.utils.CoreUtils;

import java.util.Objects;
import java.util.Optional;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Response<PayloadData> success() {

        return Response.of(ResponseCode.of(PrimaryCodeEnum.SUCCESS, SecondaryCodeEnum.BLANK), SimplePayloadData.blank());
    }

    public static <T extends PayloadData> Response<T> success(T data) {

        return Response.of(ResponseCode.of(PrimaryCodeEnum.SUCCESS, SecondaryCodeEnum.BLANK), data);
    }

    public static Response<PayloadData> unexpected() {

        return Response.of(ResponseCode.of(PrimaryCodeEnum.UNEXPECTED_ERROR, SecondaryCodeEnum.BLANK), SimplePayloadData.blank());
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

    @Nonnull
    public static SimpleResponseCode convert2SimpleResponseCode(@Nonnull ResponseCode responseCode) {
        SecondaryCode secondary = responseCode.isSecondaryBlank() ? SecondaryCodeEnum.BLANK : responseCode.getSecondary();
        String id = String.format("%02d%03d", responseCode.getPrimary().getId(), secondary.getId());
        String message = StringUtils.isBlank(secondary.getMessage()) ? responseCode.getPrimary().getMessage() : secondary.getMessage();

        return new SimpleResponseCode(id, message);
    }

    @Nonnull
    public static ResponseCode convert2ResponseCode(@Nonnull SimpleResponseCode simpleResponseCode) {
        int primaryId = Integer.parseInt(simpleResponseCode.getId().substring(0, 2));
        int secondaryId = Integer.parseInt(simpleResponseCode.getId().substring(2));
        if (secondaryId == 0) {

            return ResponseCode.of(PrimaryCode.of(primaryId, simpleResponseCode.getMessage()), SecondaryCodeEnum.BLANK);
        }

        return ResponseCode.of(PrimaryCode.of(primaryId, ""), SecondaryCode.of(secondaryId, simpleResponseCode.getMessage()));
    }

}
