package ricciliao.x.component.random;

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

}
