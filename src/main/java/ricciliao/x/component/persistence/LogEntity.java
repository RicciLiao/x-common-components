package ricciliao.x.component.persistence;

import java.io.Serializable;
import java.time.Instant;

public interface LogEntity extends Serializable {

    Instant getActionDtm();

    void setActionDtm(Instant actionDtm);

    Character getActionCd();

    void setActionCd(Character actionCd);

}
