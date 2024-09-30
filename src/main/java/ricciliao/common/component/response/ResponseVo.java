package ricciliao.common.component.response;

import java.util.Objects;

public class ResponseVo<T extends ResponseVoData> {

    public ResponseVo(Long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVo(Long code, String message) {
        this.code = code;
        this.message = message;
        data = null;
    }

    public ResponseVo(Long code) {
        this.code = code;
        message = null;
        data = null;
    }

    private final Long code;
    private final String message;
    private final T data;

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseVo)) return false;
        ResponseVo<?> that = (ResponseVo<?>) o;
        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage(), getData());
    }
}
