package ricciliao.x.component.persistence;

import java.io.Serializable;
import java.time.Instant;

public interface ModifiableEntity extends Serializable {

    Instant getCreatedDtm();

    void setCreatedDtm(Instant dtm);

    Instant getUpdatedDtm();

    void setUpdatedDtm(Instant now);

    Long getVersion();

    void setVersion(Long version);

}
