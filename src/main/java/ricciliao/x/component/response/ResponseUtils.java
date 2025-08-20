package ricciliao.x.component.response;


import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.code.impl.ResponseCodeEnum;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;

public class ResponseUtils {

    private ResponseUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Response<ResponseData> successResponse() {

        return new Response<>(ResponseCodeEnum.SUCCESS, new SimpleData.Blank());
    }

    public static <T extends ResponseData> Response<T> successResponse(T data) {

        return new Response<>(ResponseCodeEnum.SUCCESS, data);
    }

    public static <T extends ResponseData> Response<T> successResponse(ResponseCode code, T data) {

        return new Response<>(code, data);
    }

}
