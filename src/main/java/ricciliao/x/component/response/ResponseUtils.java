package ricciliao.x.component.response;


import org.springframework.validation.BindingResult;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.code.impl.ResponseCodeEnum;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;
import ricciliao.x.component.utils.CoreUtils;

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

}
