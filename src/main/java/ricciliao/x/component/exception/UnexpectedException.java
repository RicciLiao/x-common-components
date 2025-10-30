package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;

import java.io.Serial;

public class UnexpectedException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -5514526066258110102L;

    private final Throwable throwable;

    public UnexpectedException(@Nonnull SecondaryCode secondaryCode,
                               Throwable throwable) {
        super(secondaryCode);
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.UNEXPECTED_ERROR;
    }
}
