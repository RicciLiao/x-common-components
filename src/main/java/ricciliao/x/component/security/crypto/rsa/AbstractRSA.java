package ricciliao.x.component.security.crypto.rsa;

import ricciliao.x.component.security.SmartSecurity;

import java.security.Key;

public abstract class AbstractRSA extends SmartSecurity {

    public static final String CIPHER_ALGO = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    protected AbstractRSA(byte[] input) {
        super(input);
    }

    protected abstract Key getKey();

}
