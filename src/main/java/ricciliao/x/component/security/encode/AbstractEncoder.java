package ricciliao.x.component.security.encode;

import ricciliao.x.component.security.SmartSecurity;

public abstract class AbstractEncoder extends SmartSecurity {

    protected AbstractEncoder(byte[] input) {
        super(input);
    }

    public abstract String encode();

    public abstract boolean matches(String encoded);

}
