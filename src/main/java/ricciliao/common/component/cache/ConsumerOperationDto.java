package ricciliao.common.component.cache;

import java.io.Serial;
import java.io.Serializable;

public class ConsumerOperationDto<T extends RedisCacheBo> implements Serializable {
    @Serial
    private static final long serialVersionUID = 8823480414531870531L;

    private Integer operationId;
    @ConsumerOperationData
    private T data;

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
