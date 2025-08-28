package ricciliao.x.component.response.code;


import ricciliao.x.component.response.code.impl.SecondaryCodeEnum;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public interface ResponseCode extends Serializable {

    PrimaryCode getPrimary();

    SecondaryCode getSecondary();

    default boolean isSecondaryBlank() {

        return Objects.isNull(this.getSecondary()) || this.getSecondary().getId() == SecondaryCodeEnum.BLANK.getId();
    }

    static ResponseCode of(PrimaryCode primaryCode, SecondaryCode secondaryCode) {

        return new ResponseCode() {
            @Serial
            private static final long serialVersionUID = -8131353993254154406L;

            @Override
            public PrimaryCode getPrimary() {

                return primaryCode;
            }

            @Override
            public SecondaryCode getSecondary() {

                return secondaryCode;
            }
        };
    }

}
