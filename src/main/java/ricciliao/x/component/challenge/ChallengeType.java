package ricciliao.x.component.challenge;

public enum ChallengeType {

    CAPTCHA(CaptchaGenerator.class),
    VERIFICATION_CODE(VerificationGenerator.class);

    private final Class<? extends ChallengeGenerator> clazz;

    ChallengeType(Class<? extends ChallengeGenerator> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ChallengeGenerator> getClazz() {
        return clazz;
    }

}
