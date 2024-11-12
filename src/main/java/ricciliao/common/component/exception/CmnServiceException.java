package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseCode;

import java.io.Serial;

public class CmnServiceException extends CmnException {
    @Serial
    private static final long serialVersionUID = -5514526066258110102L;

    private final Throwable throwable;

    public CmnServiceException(Throwable throwable) {
        super();
        this.throwable = throwable;
    }

    public CmnServiceException(Throwable throwable, ResponseCode code) {
        super(code);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
