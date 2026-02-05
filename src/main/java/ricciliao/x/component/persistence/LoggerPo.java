package ricciliao.x.component.persistence;

import java.io.Serializable;
import java.time.Instant;

public interface LoggerPo extends Serializable {

    Instant getActionDtm();

    void setActionDtm(Instant actionDtm);

    Character getActionCd();

    void setActionCd(Character actionCd);

    Long getActionBy();

    void setActionBy(Long actionBy);

}
