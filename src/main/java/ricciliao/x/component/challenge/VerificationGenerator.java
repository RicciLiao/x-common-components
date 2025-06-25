package ricciliao.x.component.challenge;

import ricciliao.x.component.random.RandomGenerator;

public final class VerificationGenerator extends ChallengeGenerator {

    public VerificationGenerator() {
        super(0, 0, 6);
    }

    @Override
    String generate() {

        return RandomGenerator.nextString(this.codeLength)
                .clear(true)
                .allAtLeast(1)
                .generate();
    }

}
