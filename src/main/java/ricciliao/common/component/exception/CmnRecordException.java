package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseCode;

import java.io.Serial;

public class CmnRecordException extends CmnException {
    @Serial
    private static final long serialVersionUID = -6069708940719558764L;

    public CmnRecordException() {
        super();
    }

    public CmnRecordException(ResponseCode code) {
        super(code);
    }
}
