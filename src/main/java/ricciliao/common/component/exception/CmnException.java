package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseCode;
import ricciliao.common.component.response.ResponseVoCode;

public class CmnException extends Exception {
    private static final long serialVersionUID = 486238316705445980L;

    private final long code;
    private final String message;

    public CmnException() {
        super();
        this.code = ResponseCode.CommonCode.SYSTEM_ERROR.getCode();
        this.message = ResponseCode.CommonCode.SYSTEM_ERROR.getMessage();
    }

    public CmnException(ResponseVoCode voCode) {
        this.code = voCode.getCode();
        this.message = voCode.getMessage();
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
