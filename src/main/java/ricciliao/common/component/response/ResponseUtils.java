package ricciliao.common.component.response;

import org.springframework.validation.BindingResult;

import java.util.List;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseVo<ResponseEmptyData> successResponse() {

        return builder(new ResponseEmptyData()).code(ResponseCodeEnum.SUCCESS).build();
    }

    public static ResponseVo<ResponseEmptyData> errorResponse() {

        return builder(new ResponseEmptyData()).code(ResponseCodeEnum.SYSTEM_ERROR).build();
    }

    public static <T extends ResponseData> ResponseVo<T> successResponse(T data) {

        return builder(data).code(ResponseCodeEnum.SUCCESS).build();
    }

    public static ResponseBuilder<ResponseEmptyData> builder() {

        return new ResponseBuilder<>(new ResponseEmptyData());
    }

    public static <T extends ResponseData> ResponseBuilder<T> builder(T data) {

        return new ResponseBuilder<>(data);
    }

    public static ResponseBuilder<ResponseFieldViolationData> builder(List<ResponseFieldViolation> data) {

        return new ResponseBuilder<>(new ResponseFieldViolationData(data));
    }

    public static ResponseBuilder<ResponseFieldViolationData> builder(BindingResult data) {

        return new ResponseBuilder<>(
                new ResponseFieldViolationData(
                        data.getFieldErrors().stream()
                                .map(fieldError -> new ResponseFieldViolation(fieldError.getField(), fieldError.getDefaultMessage()))
                                .toList())
        ).code(ResponseCodeEnum.PARAMETER_ERROR);
    }

}
