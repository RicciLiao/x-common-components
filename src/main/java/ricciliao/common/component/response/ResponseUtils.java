package ricciliao.common.component.response;

import org.springframework.validation.BindingResult;

import java.util.List;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseVo<ResponseData> successResponse() {

        return builder(new ResponseEmptyData()).code(ResponseCodeEnum.SUCCESS).build();
    }

    public static ResponseVo<ResponseData> errorResponse() {

        return builder(new ResponseEmptyData()).code(ResponseCodeEnum.SYSTEM_ERROR).build();
    }

    public static ResponseVo<ResponseData> successResponse(ResponseData data) {

        return builder(data).code(ResponseCodeEnum.SUCCESS).build();
    }

    public static ResponseBuilder builder() {

        return new ResponseBuilder(new ResponseEmptyData());
    }

    public static ResponseBuilder builder(ResponseData data) {

        return new ResponseBuilder(data);
    }

    public static ResponseBuilder builder(List<ResponseFieldViolation> data) {

        return new ResponseBuilder(new ResponseFieldViolationData(data));
    }

    public static ResponseBuilder builder(BindingResult data) {

        return new ResponseBuilder(
                new ResponseFieldViolationData(
                        data.getFieldErrors().stream()
                                .map(fieldError -> new ResponseFieldViolation(fieldError.getField(), fieldError.getDefaultMessage()))
                                .toList())
        ).code(ResponseCodeEnum.PARAMETER_ERROR);
    }

}
