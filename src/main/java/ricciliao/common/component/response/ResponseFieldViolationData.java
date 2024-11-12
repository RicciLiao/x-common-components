package ricciliao.common.component.response;

import java.io.Serial;
import java.util.List;
import java.util.Objects;

public class ResponseFieldViolationData implements ResponseData {
    @Serial
    private static final long serialVersionUID = -5557245401566291933L;

    protected ResponseFieldViolationData(List<ResponseFieldViolation> fieldViolationDataList) {
        this.fieldViolationList = fieldViolationDataList;
    }

    private final List<ResponseFieldViolation> fieldViolationList;

    public List<ResponseFieldViolation> getFieldViolationList() {
        return fieldViolationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseFieldViolationData that)) return false;
        return Objects.equals(getFieldViolationList(), that.getFieldViolationList());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFieldViolationList());
    }
}
