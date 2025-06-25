package ricciliao.x.component.challenge;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Challenge implements Serializable {
    @Serial
    private static final long serialVersionUID = -8886903616589026103L;
    private ChallengeType type;
    private String code;
    private String image;
    public Challenge() {
    }
    public Challenge(ChallengeType type, String code, String image) {
        this.type = type;
        this.code = code;
        this.image = image;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Challenge result)) return false;
        return getType() == result.getType() && Objects.equals(getCode(), result.getCode()) && Objects.equals(getImage(), result.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getCode(), getImage());
    }
}
