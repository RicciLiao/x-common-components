package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseVoCode;

public class ServiceException extends CmnException {
    private static final long serialVersionUID = -5514526066258110102L;

    private final Throwable throwable;

    public ServiceException(Throwable throwable) {
        super();
        this.throwable = throwable;
    }

    public ServiceException(Throwable throwable, ResponseVoCode voCode) {
        super(voCode);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
