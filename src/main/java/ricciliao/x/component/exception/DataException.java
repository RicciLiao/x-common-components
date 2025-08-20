package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.Secondary;
import ricciliao.x.component.response.code.impl.PrimaryEnum;

import java.io.Serial;

public class DataException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -6069708940719558764L;

    protected DataException(@Nonnull Secondary secondaryCode) {
        super(secondaryCode);
    }

    @Override
    Primary getPrimaryCode() {

        return PrimaryEnum.UNEXPECTED_ERROR;
    }
}
