package ricciliao.common.component.exception;

import ricciliao.common.component.response.FieldViolationData;
import ricciliao.common.component.response.ResponseVoCode;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

public class ParameterException extends CmnException {
    @Serial
    private static final long serialVersionUID = -6737562056794016208L;

    private final boolean fieldViolation;
    private final List<FieldViolationData> fieldViolationList;

    public ParameterException() {
        super();
        this.fieldViolation = false;
        this.fieldViolationList = Collections.emptyList();
    }

    public ParameterException(ResponseVoCode code) {
        super(code);
        this.fieldViolation = false;
        this.fieldViolationList = Collections.emptyList();
    }

    public ParameterException(List<FieldViolationData> fieldViolationList, ResponseVoCode voCode) {
        super(voCode);
        this.fieldViolation = true;
        this.fieldViolationList = fieldViolationList;
    }

    public boolean fieldViolation() {
        return this.fieldViolation;
    }

    public List<FieldViolationData> getFieldViolationList() {
        return this.fieldViolationList;
    }
}
