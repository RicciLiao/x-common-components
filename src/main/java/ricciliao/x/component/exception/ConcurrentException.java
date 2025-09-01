package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.PrimaryCode;
import ricciliao.x.component.response.code.SecondaryCode;
import ricciliao.x.component.response.code.impl.PrimaryCodeEnum;

import java.io.Serial;

public class ConcurrentException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -6404768982745779640L;

    public ConcurrentException(@Nonnull SecondaryCode secondaryCode) {
        super(secondaryCode);
    }

    @Override
    PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.CONCURRENT_ERROR;
    }
}
