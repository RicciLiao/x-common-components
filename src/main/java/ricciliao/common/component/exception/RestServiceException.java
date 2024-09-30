package ricciliao.common.component.exception;

public class RestServiceException extends CmnException {

    public RestServiceException() {
        super();
    }

    public RestServiceException(String message) {
        super(message);
    }

    public RestServiceException(int responseCode) {
        super(responseCode);
    }

    public RestServiceException(int responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

}
