package ricciliao.x.component.payload.response.code;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class SimpleResponseCode implements Serializable {
    @Serial
    private static final long serialVersionUID = 7914111788331551865L;

    private final String id;
    private final String message;

    public SimpleResponseCode(String id, String message) {
        this.id = id;
        this.message = message;
    }

    private SimpleResponseCode() {
        this.id = null;
        this.message = null;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SimpleResponseCode that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMessage());
    }
}
