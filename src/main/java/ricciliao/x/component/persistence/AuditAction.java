package ricciliao.x.component.persistence;

import java.time.Instant;
import java.util.function.Function;

public interface AuditAction {

    static <T extends AuditSupport> Op<T> insert(Instant instant) {

        return t -> {
            t.setCreatedDtm(instant);
            t.setUpdatedDtm(instant);
            t.setVersion(null);

            return t;
        };
    }

    static <T extends AuditSupport> Op<T> update(Instant instant) {

        return t -> {
            t.setUpdatedDtm(instant);

            return t;
        };
    }

    static <T extends AuditSupport> Op<T> delete(Instant instant) {

        return AuditAction.update(instant);
    }

    @FunctionalInterface
    interface Op<T extends AuditSupport> extends Function<T, T> {

    }

}
