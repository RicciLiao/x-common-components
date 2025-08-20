package ricciliao.x.component.exception;

import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.Secondary;

import java.io.Serial;

public abstract class AbstractException extends Exception {
    @Serial
    private static final long serialVersionUID = 486238316705445980L;

    private final Secondary secondaryCode;

    protected AbstractException(@Nonnull Secondary secondaryCode) {
        this.secondaryCode = secondaryCode;
    }

    @Override
    public String getMessage() {

        return secondaryCode.getMessage();
    }

    abstract Primary getPrimaryCode();

    public Secondary getSecondaryCode() {
        return secondaryCode;
    }
}
