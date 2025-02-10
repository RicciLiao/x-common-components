package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseCode;
import ricciliao.common.component.response.ResponseCodeEnum;

import java.io.Serial;

public class CmnException extends Exception {
    @Serial
    private static final long serialVersionUID = 486238316705445980L;

    private final ResponseCode code;

    public CmnException() {
        super();
        this.code = ResponseCodeEnum.SYSTEM_ERROR;
    }

    public CmnException(ResponseCode code) {
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }

    @Override
    public String getMessage() {

        return code.getMessage();
    }
}
