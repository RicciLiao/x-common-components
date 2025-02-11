package ricciliao.common.component.cache.pojo;

import ricciliao.common.component.random.RandomGenerator;
import ricciliao.common.component.response.ResponseData;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Objects;

public class CacheDto implements ResponseData {
    @Serial
    private static final long serialVersionUID = -5939196155346350122L;

    private final String cacheId = RandomGenerator.nextString(12).allAtLeast(3).generate();
    private LocalDateTime createdDtm = LocalDateTime.now();
    private LocalDateTime updatedDtm = LocalDateTime.now();

    public String getCacheId() {
        return cacheId;
    }

    public LocalDateTime getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(LocalDateTime createdDtm) {
        this.createdDtm = createdDtm;
    }

    public LocalDateTime getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(LocalDateTime updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheDto that)) return false;
        return Objects.equals(getCacheId(), that.getCacheId()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCacheId(), getCreatedDtm(), getUpdatedDtm());
    }
}
