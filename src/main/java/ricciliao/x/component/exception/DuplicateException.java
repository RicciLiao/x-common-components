package ricciliao.x.component.exception;

import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;

import javax.annotation.Nonnull;
import java.io.Serial;


public class DuplicateException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -5832511450973767495L;

    public DuplicateException(@Nonnull SecondaryCode secondaryCode) {
        super(secondaryCode);
    }

    @Override
    public PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.DUPLICATE_ERROR;
    }

}
