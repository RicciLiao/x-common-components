package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;

public class ConcurrentException extends CmnException {
    @Serial
    private static final long serialVersionUID = -6404768982745779640L;

    public ConcurrentException() {
        super();
    }

    public ConcurrentException(ResponseVoCode code) {
        super(code);
    }
}
