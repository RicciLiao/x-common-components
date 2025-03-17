package ricciliao.x.component.response;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public record ResponseFieldViolation(String fieldName, String message) implements Serializable {
    @Serial
    private static final long serialVersionUID = 3569158049326713189L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseFieldViolation that)) return false;
        return Objects.equals(fieldName, that.fieldName) && Objects.equals(message, that.message);
    }

}
