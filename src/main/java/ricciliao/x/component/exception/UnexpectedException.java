package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.Secondary;
import ricciliao.x.component.response.code.impl.PrimaryEnum;

import java.io.Serial;

public class UnexpectedException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -5514526066258110102L;

    private final Throwable throwable;

    public UnexpectedException(@Nonnull Secondary secondaryCode,
                               Throwable throwable) {
        super(secondaryCode);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    Primary getPrimaryCode() {

        return PrimaryEnum.UNEXPECTED_ERROR;
    }
}
