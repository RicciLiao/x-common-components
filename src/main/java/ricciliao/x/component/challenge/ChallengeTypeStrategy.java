package ricciliao.x.component.challenge;

import jakarta.annotation.Nonnull;
import ricciliao.x.component.challenge.type.Captcha;
import ricciliao.x.component.challenge.type.Verification;
import ricciliao.x.component.sneaky.SneakyThrowUtils;

import java.util.function.Function;
import java.util.function.Supplier;

public enum ChallengeTypeStrategy {

    CAPTCHA_CODE(
            bgStrategy -> SneakyThrowUtils.get(() -> new Captcha(bgStrategy).generate()),
            () -> SneakyThrowUtils.get(() -> new Captcha().generate())
    ),
    VERIFICATION_CODE(
            bgStrategy -> SneakyThrowUtils.get(() -> new Verification(bgStrategy).generate()),
            () -> SneakyThrowUtils.get(() -> new Verification().generate())
    );

    private final Function<ChallengeBgStrategy, Challenge> strategyF;
    private final Supplier<Challenge> strategyS;


    ChallengeTypeStrategy(Function<ChallengeBgStrategy, Challenge> strategyF,
                          Supplier<Challenge> strategyS) {
        this.strategyF = strategyF;
        this.strategyS = strategyS;
    }

    public Challenge apply(@Nonnull ChallengeBgStrategy bgStrategy) {

        return strategyF.apply(bgStrategy);
    }

    public Challenge get() {

        return strategyS.get();
    }

}
