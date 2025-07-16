package ricciliao.x.component.challenge.type;

import ricciliao.x.component.challenge.ChallengeBgStrategy;
import ricciliao.x.component.challenge.ChallengeTypeGenerator;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.random.RandomGenerator;

public final class Verification extends ChallengeTypeGenerator {

    public Verification(ChallengeBgStrategy bgStrategy) {
        super(6, bgStrategy);
    }

    public Verification() {
        super(6);
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

        return ChallengeTypeStrategy.VERIFICATION_CODE;
    }

}
