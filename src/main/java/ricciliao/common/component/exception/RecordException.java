package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;

public class RecordException extends CmnException {
    @Serial
    private static final long serialVersionUID = -6069708940719558764L;

    public RecordException() {
        super();
    }

    public RecordException(ResponseVoCode code) {
        super(code);
    }
}
