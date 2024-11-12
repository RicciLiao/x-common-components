package ricciliao.common.component.response;

public class ResponseBuilder {

    private ResponseCode code;
    private final ResponseData data;

    public ResponseBuilder(ResponseData data) {
        this.data = data;
    }

    public ResponseVo<ResponseData> build() {
        return new ResponseVo<>(code.getId(), code.getMessage(), data);
    }

    public ResponseBuilder code(ResponseCode code) {
        this.code = code;

        return this;
    }

}
