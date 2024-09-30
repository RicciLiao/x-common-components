package ricciliao.common.component.response;

import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseUtil {

    private ResponseUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseVo<ResponseEmptyData> successResponse() {

        return builder(new ResponseEmptyData()).code(ResponseCode.CommonCode.SUCCESS).build();
    }


    public static ResponseVo<ResponseEmptyData> errorResponse() {

        return builder(new ResponseEmptyData()).code(ResponseCode.CommonCode.SYSTEM_ERROR).build();
    }

    public static <T extends ResponseVoData> ResponseVo<T> successResponse(T data) {

        return builder(data).code(ResponseCode.CommonCode.SUCCESS).build();
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
        ).code(ResponseCode.CommonCode.PARAMETER_ERROR);
    }

}
