package ricciliao.x.component.persistence;

import ricciliao.x.component.CoreConstants;

import java.time.Instant;
import java.util.function.Consumer;
import java.util.function.Function;

public interface LoggerAction {

    static <T extends LoggerPo> Cd<T> insert() {

        return Cd.type(CoreConstants.DATA_ACTION_TYPE_INSERT);
    }

    static <T extends LoggerPo> Cd<T> update() {

        return Cd.type(CoreConstants.DATA_ACTION_TYPE_UPDATE);
    }

    static <T extends LoggerPo> Cd<T> delete() {

        return Cd.type(CoreConstants.DATA_ACTION_TYPE_DELETE);
    }

    @FunctionalInterface
    interface Dtm<T extends LoggerPo> extends Function<Instant, T> {

        static <T extends LoggerPo> Dtm<T> instant(T po, Consumer<Instant> setActionDtm) {

            return instant -> {
                setActionDtm.accept(instant);

                return po;
            };
        }

    }

    @FunctionalInterface
    interface Cd<T extends LoggerPo> extends Function<T, Dtm<T>> {

        static <T extends LoggerPo> Cd<T> type(Character actionCd) {

            return po -> {
                po.setActionCd(actionCd);

                return Dtm.instant(po, po::setActionDtm);
            };
        }

    }

}
