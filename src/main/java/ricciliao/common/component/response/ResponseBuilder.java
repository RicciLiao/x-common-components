package ricciliao.common.component.response;

public class ResponseBuilder<T extends ResponseVoData> {

    private ResponseVoCode code;
    private final T data;

    public ResponseBuilder(T data) {
        this.data = data;
    }

    public ResponseVo<T> build() {
        return new ResponseVo<>(code.getCode(), code.getMessage(), data);
    }

    public ResponseBuilder<T> code(ResponseVoCode code) {
        this.code = code;

        return this;
    }

}
