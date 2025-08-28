package ricciliao.x.component.response.code.impl;


import ricciliao.x.component.response.code.SecondaryCode;

public enum SecondaryCodeEnum implements SecondaryCode {
    BLANK(0, ""),
    ;

    private final int id;
    private final String message;

    SecondaryCodeEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {

        return this.id;
    }

    @Override
    public String getMessage() {

        return this.message;
    }
}
