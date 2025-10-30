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

}
