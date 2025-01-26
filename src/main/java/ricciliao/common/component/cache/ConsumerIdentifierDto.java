package ricciliao.common.component.cache;


import java.io.Serial;
import java.io.Serializable;

public class ConsumerIdentifierDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5321815996902535277L;

    private String c;
    private String i;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

}
