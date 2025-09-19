package ricciliao.x.component.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class Response<T extends ResponseData> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8286232383424230504L;

    private final ResponseCode code;
    private final T data;

    public Response(ResponseCode code, T data) {
        this.code = code;
        this.data = data;
    }

    public Response(ResponseCode code) {
        this.code = code;
        data = null;
    }

    public static <T extends ResponseData> Response<T> of(ResponseCode code, T data) {

        return new Response<>(code, data);
    }

    private Response() {
        this.code = null;
        this.data = null;
    }

    public ResponseCode getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public boolean isBlankData() {

        return Objects.isNull(this.data) || this.data instanceof SimpleData.Blank;
    }

}
