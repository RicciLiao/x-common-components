package ricciliao.x.component.response;

public class ResponseBuilder<T extends ResponseData> {

    private final T data;
    private ResponseCode code;

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
