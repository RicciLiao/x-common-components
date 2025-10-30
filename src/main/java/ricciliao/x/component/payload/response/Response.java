package ricciliao.x.component.payload.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimpleData;
import ricciliao.x.component.payload.response.code.ResponseCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class Response<T extends PayloadData> implements Serializable {
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

    private Response() {
        this.code = null;
        this.data = null;
    }

    public static <T extends PayloadData> Response<T> of(ResponseCode code, T data) {

        return new Response<>(code, data);
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
