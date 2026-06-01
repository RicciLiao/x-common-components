package ricciliao.x.component.persistence;

import java.io.Serializable;
import java.time.Instant;

public interface AuditSupport extends Serializable {

    Instant getCreatedDtm();

    void setCreatedDtm(Instant dtm);

    Long getCreatedBy();

    void setCreatedBy(Long createdBy);

    Instant getUpdatedDtm();

    void setUpdatedDtm(Instant now);

    Long getUpdatedBy();

    void setUpdatedBy(Long updatedBy);

    Long getVersion();

    void setVersion(Long version);

}
