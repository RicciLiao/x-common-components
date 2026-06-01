package ricciliao.x.component.persistence;

import java.time.Instant;

public interface LogSupport extends AuditSupport {

    Instant getActionDtm();

    void setActionDtm(Instant actionDtm);

    Long getActionBy();

    void setActionBy(Long actionBy);

    Character getActionCd();

    void setActionCd(Character actionCd);

}
