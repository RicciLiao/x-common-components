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

    public long getCodeId() {
        return code.getId();
    }

    public String getCodeMessage() {
        return code.getMessage();
    }

    public ResponseCode getCode() {
        return code;
    }
}
