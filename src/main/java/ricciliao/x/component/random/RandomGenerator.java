package ricciliao.x.component.random;

import ricciliao.x.component.exception.CmnException;

import java.security.SecureRandom;

public final class RandomGenerator {

    private RandomGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static RandomStringGenerator.Builder nextString(int length) {

        return RandomStringGenerator.builder(length);
    }

    public static int randomLength(int min, int max) {

        return new SecureRandom().nextInt((max - min) + 1) + min;
    }

    public static CaptchaGenerator.CaptchaResult nextCaptcha() throws CmnException {

        return CaptchaGenerator.generateCaptchaImage(
                nextString(5)
                        .clear(true)
                        .allAtLeast(1)
                        .generate()
        );
    }

}
