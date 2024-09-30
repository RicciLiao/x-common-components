package ricciliao.common.component.exception;

import ricciliao.common.component.response.FieldViolationData;

import java.util.Collections;
import java.util.List;

public class ParameterException extends CmnException {

    private final boolean fieldViolation;
    private final List<FieldViolationData> fieldViolationList;

    public ParameterException() {
        super();
        fieldViolation = false;
        fieldViolationList = Collections.emptyList();
    }

    public ParameterException(int responseCode) {
        super(responseCode);
        fieldViolation = false;
        fieldViolationList = Collections.emptyList();
    }

    public ParameterException(String message) {
        super(message);
        fieldViolation = false;
        fieldViolationList = Collections.emptyList();
    }

    public ParameterException(int responseCode, String message) {
        super(responseCode, message);
        fieldViolation = false;
        fieldViolationList = Collections.emptyList();
    }

    public ParameterException(String message, boolean fieldViolation, List<FieldViolationData> fieldViolationList) {
        super(message);
        this.fieldViolation = fieldViolation;
        this.fieldViolationList = fieldViolationList;
    }

    public ParameterException(int responseCode, String message, boolean fieldViolation, List<FieldViolationData> fieldViolationList) {
        super(responseCode, message);
        this.fieldViolation = fieldViolation;
        this.fieldViolationList = fieldViolationList;
    }

    public boolean fieldViolation() {
        return fieldViolation;
    }

    public List<FieldViolationData> getFieldViolationList() {
        return fieldViolationList;
    }
}
