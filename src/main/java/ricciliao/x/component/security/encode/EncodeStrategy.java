package ricciliao.x.component.security.encode;

import ricciliao.x.component.security.SecurityProvider;
import ricciliao.x.component.security.SecurityStrategy;

import java.util.function.BiFunction;

public enum EncodeStrategy implements SecurityStrategy {

    ARGON2(
            (securityProvider, bytes) -> securityProvider.argon2Encoder().apply(bytes)
    ),
    ;

    private final BiFunction<SecurityProvider, byte[], AbstractEncoder> encoder;

    EncodeStrategy(BiFunction<SecurityProvider, byte[], AbstractEncoder> encoder) {
        this.encoder = encoder;
    }


    public String encode(byte[] input) {

        return encoder.apply(this.getSecurityProvider(), input).encode();
    }

    public boolean matches(byte[] plainInput, String encoded) {

        return encoder.apply(this.getSecurityProvider(), plainInput).matches(encoded);
    }
}
