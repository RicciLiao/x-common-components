package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;

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

    public PayloadData getResponseData() {

        return SimplePayloadData.blank();
    }

    public SecondaryCode getSecondaryCode() {
        return secondaryCode;
    }

}
