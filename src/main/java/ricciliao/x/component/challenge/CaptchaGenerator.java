package ricciliao.x.component.challenge;

import ricciliao.x.component.random.RandomGenerator;

public final class CaptchaGenerator extends ChallengeGenerator {


    public CaptchaGenerator() {
        super(135, 35, 5);
    }

    @Override
    String generate() {

        return RandomGenerator.nextString(this.codeLength)
                .clear(true)
                .allAtLeast(1)
                .generate();
    }

}
