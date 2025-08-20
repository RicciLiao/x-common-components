package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.Secondary;
import ricciliao.x.component.response.code.impl.PrimaryEnum;

import java.io.Serial;

public class SecurityException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 4709323374453711581L;

    protected SecurityException(@Nonnull Secondary secondaryCode) {
        super(secondaryCode);
    }

    @Override
    Primary getPrimaryCode() {

        return PrimaryEnum.SECURITY_ERROR;
    }
}
