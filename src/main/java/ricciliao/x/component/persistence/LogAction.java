package ricciliao.x.component.persistence;

import ricciliao.x.component.CoreConstants;

import java.time.Instant;
import java.util.function.Function;

public interface LogAction {

    static <T extends LogEntity> Op<T> insert(Instant instant) {

        return t -> {
            t.setActionCd(CoreConstants.DATA_ACTION_TYPE_INSERT);
            t.setActionDtm(instant);

            return t;
        };
    }

    static <T extends LogEntity> Op<T> update(Instant instant) {

        return t -> {
            t.setActionCd(CoreConstants.DATA_ACTION_TYPE_UPDATE);
            t.setActionDtm(instant);

            return t;
        };
    }

    static <T extends LogEntity> Op<T> delete(Instant instant) {

        return t -> {
            t.setActionCd(CoreConstants.DATA_ACTION_TYPE_DELETE);
            t.setActionDtm(instant);

            return t;
        };
    }

    @FunctionalInterface
    interface Op<T extends LogEntity> extends Function<T, T> {

    }

}
