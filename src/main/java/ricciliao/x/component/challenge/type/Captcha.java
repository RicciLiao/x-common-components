package ricciliao.x.component.challenge.type;

import ricciliao.x.component.challenge.ChallengeBgStrategy;
import ricciliao.x.component.challenge.ChallengeTypeGenerator;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.random.RandomGenerator;

public final class Captcha extends ChallengeTypeGenerator {

    public Captcha(ChallengeBgStrategy strategy) {
        super(5, strategy);
    }

    public Captcha() {
        super(5);
    }

    @Override
    protected String getCode(int codeLength) {

        return RandomGenerator.nextString(codeLength)
                .clear(true)
                .allAtLeast(1)
                .generate();
    }

    @Override
    protected ChallengeTypeStrategy getType() {

        return ChallengeTypeStrategy.CAPTCHA_CODE;
    }
}
