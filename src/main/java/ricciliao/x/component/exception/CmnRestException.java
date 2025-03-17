package ricciliao.x.component.exception;

import ricciliao.x.component.response.ResponseCode;

import java.io.Serial;

public class CmnRestException extends CmnException {
    @Serial
    private static final long serialVersionUID = 5362552358311799141L;

    public CmnRestException() {
        super();
    }

    public CmnRestException(ResponseCode code) {
        super(code);
    }
}
