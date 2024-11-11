package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;

public class SecurityException extends CmnException {
    @Serial
    private static final long serialVersionUID = 4709323374453711581L;

    public SecurityException() {
        super();
    }

    public SecurityException(ResponseVoCode code) {
        super(code);
    }
}
