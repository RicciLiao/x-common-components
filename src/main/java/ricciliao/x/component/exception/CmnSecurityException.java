package ricciliao.x.component.exception;

import ricciliao.x.component.response.ResponseCode;

import java.io.Serial;

public class CmnSecurityException extends CmnException {
    @Serial
    private static final long serialVersionUID = 4709323374453711581L;

    public CmnSecurityException() {
        super();
    }

    public CmnSecurityException(ResponseCode code) {
        super(code);
    }
}
