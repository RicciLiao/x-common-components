package ricciliao.common.component.exception;

import ricciliao.common.component.response.CmnResponseCode;
import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;

public class CmnException extends Exception {
    @Serial
    private static final long serialVersionUID = 486238316705445980L;

    private final ResponseVoCode code;

    public CmnException() {
        super();
        this.code = CmnResponseCode.SYSTEM_ERROR;
    }

    public CmnException(ResponseVoCode code) {
        this.code = code;
    }

    public long getCodeId() {
        return code.getId();
    }

    public String getCodeMessage() {
        return code.getMessage();
    }

    public ResponseVoCode getCode() {
        return code;
    }
}
