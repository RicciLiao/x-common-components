package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseCode;

public class CmnException extends Exception {

    private final long code;
    private final String message;

    public CmnException() {
        super();
        this.code = ResponseCode.CommonCode.SYSTEM_ERROR.getCode();
        this.message = ResponseCode.CommonCode.SYSTEM_ERROR.getMessage();
    }

    public CmnException(int code) {
        this.code = code;
        this.message = "";
    }

    public CmnException(String message) {
        super(message);
        this.code = 1;
        this.message = message;
    }

    public CmnException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
