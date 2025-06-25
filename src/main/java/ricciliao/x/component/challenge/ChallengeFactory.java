package ricciliao.x.component.challenge;

import jakarta.annotation.Nonnull;
import ricciliao.x.component.exception.CmnException;

import java.util.Base64;
import java.util.Map;

public class ChallengeFactory {

    private static final Map<ChallengeType, ChallengeGenerator> MAP = Map.of(
            ChallengeType.CAPTCHA, new CaptchaGenerator(),
            ChallengeType.VERIFICATION_CODE, new VerificationGenerator()
    );

    private ChallengeFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static ChallengeBuilder nextChallenge(ChallengeType type) {

        return new ChallengeBuilder(type);
    }

    private static Challenge generate(@Nonnull ChallengeBuilder builder) throws CmnException {
        ChallengeGenerator generator = MAP.get(builder.type);
        String code = generator.generate();
        if (builder.withImage) {

            return new Challenge(builder.type, code, Base64.getEncoder().encodeToString(generator.generate(code)));
        }

        return new Challenge(builder.type, code, null);
    }


    public static class ChallengeBuilder {
        private final ChallengeType type;
        private boolean withImage = false;

        public ChallengeBuilder(ChallengeType type) {
            this.type = type;
        }

        public ChallengeBuilder withImage(boolean b) {
            this.withImage = b;

            return this;
        }

        public Challenge generate() throws CmnException {

            return ChallengeFactory.generate(this);
        }
    }

}
