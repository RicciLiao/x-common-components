package ricciliao.common.component.exception;

public class SecurityException extends CmnException {

    public SecurityException() {
        super();
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(int responseCode) {
        super(responseCode);
    }

    public SecurityException(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

}
