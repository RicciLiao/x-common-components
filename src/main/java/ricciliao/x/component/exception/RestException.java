package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;

import java.io.Serial;

public class RestException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 5362552358311799141L;

    public RestException(@Nonnull SecondaryCode secondaryCode) {
        super(secondaryCode);
    }

    @Override
    public PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.REST_ERROR;
    }
}
