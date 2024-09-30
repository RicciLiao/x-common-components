package ricciliao.common.component.exception;

public class ConcurrentException extends CmnException {

    public ConcurrentException() {
        super();
    }

    public ConcurrentException(String message) {
        super(message);
    }

    public ConcurrentException(int responseCode) {
        super(responseCode);
    }

    public ConcurrentException(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

}
