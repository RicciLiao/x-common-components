package ricciliao.common.component.exception;

import ricciliao.common.component.response.ResponseFieldViolation;
import ricciliao.common.component.response.ResponseCode;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

public class CmnParameterException extends CmnException {
    @Serial
    private static final long serialVersionUID = -6737562056794016208L;

    private final boolean fieldViolation;
    private final List<ResponseFieldViolation> fieldViolationList;

    public CmnParameterException() {
        super();
        this.fieldViolation = false;
        this.fieldViolationList = Collections.emptyList();
    }

    public CmnParameterException(ResponseCode code) {
        super(code);
        this.fieldViolation = false;
        this.fieldViolationList = Collections.emptyList();
    }

    public CmnParameterException(List<ResponseFieldViolation> fieldViolationList, ResponseCode voCode) {
        super(voCode);
        this.fieldViolation = true;
        this.fieldViolationList = fieldViolationList;
    }

    public boolean fieldViolation() {
        return this.fieldViolation;
    }

    public List<ResponseFieldViolation> getFieldViolationList() {
        return this.fieldViolationList;
    }
}
