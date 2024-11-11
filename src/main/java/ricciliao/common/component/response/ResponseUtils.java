package ricciliao.common.component.response;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseVo<ResponseEmptyData> successResponse() {

        return builder(new ResponseEmptyData()).code(CmnResponseCode.SUCCESS).build();
    }


    public static ResponseVo<ResponseEmptyData> errorResponse() {

        return builder(new ResponseEmptyData()).code(CmnResponseCode.SYSTEM_ERROR).build();
    }

    public static <T extends ResponseVoData> ResponseVo<T> successResponse(T data) {

        return builder(data).code(CmnResponseCode.SUCCESS).build();
    }

    public static <T extends ResponseVoData> ResponseBuilder<ResponseEmptyData> builder() {

        return new ResponseBuilder<>(new ResponseEmptyData());
    }

    public static <T extends ResponseVoData> ResponseBuilder<T> builder(T data) {

        return new ResponseBuilder<>(data);
    }

    public static ResponseBuilder<ResponseFieldViolationData> builder(List<FieldViolationData> data) {

        return new ResponseBuilder<>(new ResponseFieldViolationData(data));
    }

    public static ResponseBuilder<ResponseFieldViolationData> builder(BindingResult data) {

        return new ResponseBuilder<>(
                new ResponseFieldViolationData(
                        data.getFieldErrors().stream()
                                .map(fieldError -> new FieldViolationData(fieldError.getField(), fieldError.getDefaultMessage()))
                                .collect(Collectors.toList()))
        ).code(CmnResponseCode.PARAMETER_ERROR);
    }

}
