package ricciliao.x.component.payload.response.code;

import java.io.Serial;

public interface PrimaryCode extends CodeLevel {

    static PrimaryCode of(int id, String message) {

        return new PrimaryCode() {
            @Serial
            private static final long serialVersionUID = -7635197187959251273L;

            @Override
            public int getId() {

                return id;
            }

            @Override
            public String getMessage() {

                return message;
            }
        };
    }

    // 协变返回类型(Covariant Return Type) CodeLevel ---> PrimaryCode
    @Override
    default PrimaryCode format(Object... args) {

        return (PrimaryCode) CodeLevel.super.format(args);
    }
}
