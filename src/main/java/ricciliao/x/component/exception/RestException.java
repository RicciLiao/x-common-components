package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.Secondary;
import ricciliao.x.component.response.code.impl.PrimaryEnum;

import java.io.Serial;

public class RestException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 5362552358311799141L;

    protected RestException(@Nonnull Secondary secondaryCode) {
        super(secondaryCode);
    }

    @Override
    Primary getPrimaryCode() {

        return PrimaryEnum.REST_ERROR;
    }
}
