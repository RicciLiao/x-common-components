package ricciliao.common.component.response;

import java.util.List;
import java.util.Objects;

public class ResponseFieldViolationData implements ResponseVoData {
    private static final long serialVersionUID = -5557245401566291933L;

    protected ResponseFieldViolationData(List<FieldViolationData> fieldViolationDataList) {
        this.fieldViolationDataList = fieldViolationDataList;
    }

    private final List<FieldViolationData> fieldViolationDataList;

    public List<FieldViolationData> getFieldViolationDataList() {
        return fieldViolationDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseFieldViolationData)) return false;
        ResponseFieldViolationData that = (ResponseFieldViolationData) o;
        return Objects.equals(getFieldViolationDataList(), that.getFieldViolationDataList());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFieldViolationDataList());
    }
}
