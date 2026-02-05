package ricciliao.x.component.payload.response.code;

import java.io.Serial;

public interface SecondaryCode extends CodeLevel {

    static SecondaryCode of(int id, String message) {

        return new SecondaryCode() {
            @Serial
            private static final long serialVersionUID = -1340789690815887485L;

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

    // 协变返回类型(Covariant Return Type) CodeLevel ---> SecondaryCode
    @Override
    default SecondaryCode format(Object... args) {

        return (SecondaryCode) CodeLevel.super.format(args);
    }
}
