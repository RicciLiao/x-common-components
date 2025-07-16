package ricciliao.x.component.challenge;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Challenge implements Serializable {
    @Serial
    private static final long serialVersionUID = -8886903616589026103L;

    public Challenge() {
    }

    public Challenge(ChallengeTypeStrategy strategy, String code) {
        this.strategy = strategy;
        this.code = code;
    }

    public Challenge(ChallengeTypeStrategy strategy, String code, String image) {
        this.strategy = strategy;
        this.code = code;
        this.image = image;
    }

    private ChallengeTypeStrategy strategy;
    private String code;
    private String image;

    public ChallengeTypeStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ChallengeTypeStrategy strategy) {
        this.strategy = strategy;
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
        return getStrategy() == result.getStrategy() && Objects.equals(getCode(), result.getCode()) && Objects.equals(getImage(), result.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStrategy(), getCode(), getImage());
    }
}
