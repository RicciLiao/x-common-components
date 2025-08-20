package ricciliao.x.component.response;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.data.ResponseData;

import java.io.Serial;
import java.io.Serializable;

public class Response<T extends ResponseData> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8286232383424230504L;
    private final ResponseCode code;
    private final T data;


    public Response(@Nonnull ResponseCode code, T data) {
        this.code = code;
        this.data = data;
    }
    public Response(@Nonnull ResponseCode code) {
        this.code = code;
        data = null;
    }

    public ResponseCode getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

}
