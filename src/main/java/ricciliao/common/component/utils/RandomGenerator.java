package ricciliao.common.component.utils;

import java.util.Random;

public final class RandomGenerator {

    public static RandomStringGenerator.Builder nextString(int length) {

        return RandomStringGenerator.builder(length);
    }

    public static int randomLength(int min, int max) {

        return new Random().nextInt((max - min) + 1) + min;
    }

}
