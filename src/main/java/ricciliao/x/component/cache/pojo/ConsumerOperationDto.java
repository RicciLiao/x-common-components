package ricciliao.x.component.cache.pojo;

import ricciliao.x.component.cache.consumer.ConsumerData;
import ricciliao.x.component.random.RandomGenerator;
import ricciliao.x.component.response.ResponseData;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ConsumerOperationDto<T extends CacheDto> implements Serializable, ResponseData {
    @Serial
    private static final long serialVersionUID = 8823480414531870531L;

    public ConsumerOperationDto() {
    }

    public ConsumerOperationDto(T data) {
        this.data = data;
    }

    public ConsumerOperationDto(T data, Long ttlOfMillis) {
        this.ttlOfMillis = ttlOfMillis;
        this.data = data;
    }

    private final String id = RandomGenerator.nextString(12).allAtLeast(3).generate();
    private Long ttlOfMillis;
    @ConsumerData
    private T data;

    public String getId() {
        return id;
    }

    public Long getTtlOfMillis() {
        return ttlOfMillis;
    }

    public void setTtlOfMillis(Long ttlOfMillis) {
        this.ttlOfMillis = ttlOfMillis;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumerOperationDto<?> that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTtlOfMillis(), that.getTtlOfMillis()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTtlOfMillis(), getData());
    }
}
