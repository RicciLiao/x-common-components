package ricciliao.x.component.exception;

import ricciliao.x.component.response.ResponseCode;

import java.io.Serial;

public class ConcurrentException extends CmnException {
    @Serial
    private static final long serialVersionUID = -6404768982745779640L;

    public ConcurrentException() {
        super();
    }

    public ConcurrentException(ResponseCode code) {
        super(code);
    }
}
