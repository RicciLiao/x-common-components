package ricciliao.common.component.response;

import java.util.Objects;

public class FieldViolationData implements ResponseVoData {
    private static final long serialVersionUID = 3569158049326713189L;

    public FieldViolationData(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public final String fieldName;
    public final String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FieldViolationData)) return false;
        FieldViolationData that = (FieldViolationData) o;
        return Objects.equals(fieldName, that.fieldName) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, message);
    }
}
