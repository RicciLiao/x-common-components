package ricciliao.common.component.exception;

public class ServiceException extends CmnException {

    private final Throwable throwable;

    public ServiceException() {
        super();
        throwable = null;
    }

    public ServiceException(Throwable throwable) {
        this.throwable = throwable;
    }

    public ServiceException(String message) {
        super(message);
        throwable = null;
    }

    public ServiceException(int responseCode) {
        super(responseCode);
        throwable = null;
    }

    public ServiceException(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
        throwable = null;
    }

    public Throwable getThrowable() {
        return throwable;
    }

}
