package ricciliao.x.component.security;

public abstract class SmartSecurity {

    protected final byte[] input;

    protected SmartSecurity(byte[] input) {
        this.input = input;
    }

}
