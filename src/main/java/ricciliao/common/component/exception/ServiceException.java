package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;

public class ServiceException extends CmnException {
    @Serial
    private static final long serialVersionUID = -5514526066258110102L;

    private final Throwable throwable;

    public ServiceException(Throwable throwable) {
        super();
        this.throwable = throwable;
    }

    public ServiceException(Throwable throwable, ResponseVoCode code) {
        super(code);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
