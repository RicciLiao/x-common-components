package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;

public class RestServiceException extends CmnException {
    @Serial
    private static final long serialVersionUID = 5362552358311799141L;

    public RestServiceException() {
        super();
    }

    public RestServiceException(ResponseVoCode code) {
        super(code);
    }
}
