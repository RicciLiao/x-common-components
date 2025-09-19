package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.PrimaryCode;
import ricciliao.x.component.response.code.SecondaryCode;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;

import java.io.Serial;

public abstract class AbstractException extends Exception {
    @Serial
    private static final long serialVersionUID = 486238316705445980L;

    private final SecondaryCode secondaryCode;

    protected AbstractException(@Nonnull SecondaryCode secondaryCode) {
        this.secondaryCode = secondaryCode;
    }

    @Override
    public String getMessage() {

        return secondaryCode.getMessage();
    }

    public abstract PrimaryCode getPrimaryCode();

    public ResponseData getResponseData() {

        return SimpleData.blank();
    }

    public SecondaryCode getSecondaryCode() {
        return secondaryCode;
    }

}
