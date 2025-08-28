package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.PrimaryCode;
import ricciliao.x.component.response.code.SecondaryCode;
import ricciliao.x.component.response.code.impl.PrimaryCodeEnum;

import java.io.Serial;

public class SecurityException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 4709323374453711581L;

    protected SecurityException(@Nonnull SecondaryCode secondaryCode) {
        super(secondaryCode);
    }

    @Override
    PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.SECURITY_ERROR;
    }
}
