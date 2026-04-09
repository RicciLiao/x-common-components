package ricciliao.x.component.payload.response.code;


import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ResponseCode implements Serializable {
    @Serial
    private static final long serialVersionUID = -7155703590971461602L;
    private final PrimaryCode primaryCode;
    private final SecondaryCode secondaryCode;

    public ResponseCode(PrimaryCode primaryCode, SecondaryCode secondaryCode) {
        this.primaryCode = primaryCode;
        this.secondaryCode = secondaryCode;
    }

    public PrimaryCode getPrimary() {
        return primaryCode;
    }

    public SecondaryCode getSecondary() {
        return secondaryCode;
    }

    public boolean isSecondaryBlank() {

        return Objects.isNull(this.getSecondary()) || this.getSecondary().getId() == SecondaryCodeEnum.BLANK.getId();
    }

    public static ResponseCode of(PrimaryCode primaryCode, SecondaryCode secondaryCode) {

        return new ResponseCode(primaryCode, secondaryCode);
    }

}
