package ricciliao.common.component.response;

public class ResponseBuilder<T extends ResponseData> {

    private ResponseCode code;
    private final T data;

    public ResponseBuilder(T data) {
        this.data = data;
    }

    public ResponseVo<T> build() {
        return new ResponseVo<>(code.getId(), code.getMessage(), data);
    }

    public ResponseBuilder<T> code(ResponseCode code) {
        this.code = code;

        return this;
    }

}
