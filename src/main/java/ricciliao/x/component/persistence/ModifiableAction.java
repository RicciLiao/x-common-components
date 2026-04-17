package ricciliao.x.component.persistence;

import java.time.Instant;
import java.util.function.Function;

public interface ModifiableAction {

    static <T extends ModifiableEntity> Op<T> insert(Instant instant) {

        return t -> {
            t.setCreatedDtm(instant);
            t.setUpdatedDtm(instant);
            t.setVersion(null);

            return t;
        };
    }

    static <T extends ModifiableEntity> Op<T> update(Instant instant) {

        return t -> {
            t.setUpdatedDtm(instant);

            return t;
        };
    }

    static <T extends ModifiableEntity> Op<T> delete(Instant instant) {

        return ModifiableAction.update(instant);
    }

    @FunctionalInterface
    interface Op<T extends ModifiableEntity> extends Function<T, T> {

    }

}
