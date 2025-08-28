package ricciliao.x.component.response.code;

import java.io.Serializable;

public interface CodeLevel extends Serializable {

    int getId();

    String getMessage();

    static CodeLevel of(int id, String message) {

        return new CodeLevel() {
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
