package ricciliao.x.component.random;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomStringGenerator {

    private final Builder builder;
    private final SecureRandom random;

    private static final String LETTER_UPPER_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETTER_LOWER_POOL = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGIT_POOL = "0123456789";

    private String letterUpperPool = LETTER_UPPER_POOL;
    private String letterLowerPool = LETTER_LOWER_POOL;
    private String digitPool = DIGIT_POOL;

    private RandomStringGenerator(Builder builder) {
        this.builder = builder;
        this.random = new SecureRandom();
        if (builder.clear) {
            this.letterUpperPool =
                    LETTER_UPPER_POOL
                            .replace("L", "").replace("O", "")
                            .replace("I", "").replace("O", "");
            this.letterLowerPool =
                    LETTER_LOWER_POOL
                            .replace("l", "").replace("o", "")
                            .replace("i", "").replace("o", "");
            this.digitPool =
                    DIGIT_POOL
                            .replace("1", "").replace("0", "");
        }
    }

    private String generate() {
        List<Character> characters = new ArrayList<>();
        StringBuilder pool = new StringBuilder();
        for (int i = 0; i < this.builder.atLeastUpperLetter; i++) {
            characters.add(letterUpperPool.charAt(random.nextInt(letterUpperPool.length())));
            pool.append(letterUpperPool);
        }
        for (int i = 0; i < this.builder.atLeastLowerLetter; i++) {
            characters.add(letterLowerPool.charAt(random.nextInt(letterLowerPool.length())));
            pool.append(letterLowerPool);
        }
        for (int i = 0; i < this.builder.atLeastLetter - characters.size(); i++) {
            characters.add(pool.charAt(random.nextInt(pool.length())));
        }
        for (int i = 0; i < this.builder.atLeastDigit; i++) {
            characters.add(digitPool.charAt(random.nextInt(digitPool.length())));
            pool.append(digitPool);
        }
        if (pool.isEmpty()) {
            pool.append(letterUpperPool).append(letterLowerPool).append(digitPool);
        }
        int remainder = this.builder.length - characters.size();
        for (int i = 0; i < remainder; i++) {
            characters.add(pool.charAt(random.nextInt(pool.length())));
        }
        Collections.shuffle(characters);
        StringBuilder sbr = new StringBuilder(this.builder.length);
        for (char c : characters) {
            sbr.append(c);
        }

        return sbr.toString();
    }

    static Builder builder(int length) {

        return new Builder(length);
    }

    public static final class Builder {
        private boolean clear = false;
        private final int length;
        private int atLeastLetter = 0;
        private int atLeastUpperLetter = 0;
        private int atLeastLowerLetter = 0;
        private int atLeastDigit = 0;

        private Builder(int length) {
            this.length = length;
        }

        public Builder atLeastLetter(int i) {
            this.atLeastLetter = i;

            return this;
        }

        public Builder atLeastUpperLetter(int i) {
            this.atLeastUpperLetter = i;

            return this;
        }

        public Builder atLeastLowerLetter(int i) {
            this.atLeastLowerLetter = i;

            return this;
        }

        public Builder atLeastDigit(int i) {
            this.atLeastDigit = i;

            return this;
        }

        public Builder clear(boolean clear) {
            this.clear = clear;

            return this;
        }

        public Builder allAtLeast(int i) {
            this.atLeastUpperLetter = i;
            this.atLeastLowerLetter = i;
            this.atLeastDigit = i;

            return this;
        }

        public String generate() {

            return new RandomStringGenerator(this).generate();
        }

    }

}
