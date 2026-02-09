package ricciliao.x.component.persistence;

import java.io.Serializable;
import java.time.Instant;

public interface ModifiablePo extends Serializable {

    Instant getCreatedDtm();

    void setCreatedDtm(Instant dtm);

    Instant getUpdatedDtm();

    void setUpdatedDtm(Instant now);



}
