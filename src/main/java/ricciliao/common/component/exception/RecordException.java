package ricciliao.common.component.exception;

public class RecordException extends CmnException {

    public RecordException() {
        super();
    }

    public RecordException(String message) {
        super(message);
    }

    public RecordException(int responseCode) {
        super(responseCode);
    }

    public RecordException(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

}
