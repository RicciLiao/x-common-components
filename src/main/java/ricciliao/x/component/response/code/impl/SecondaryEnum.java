package ricciliao.x.component.response.code.impl;

import ricciliao.x.component.response.code.Secondary;

public enum SecondaryEnum implements Secondary {
    BLANK(0, ""),
    ;

    private final int id;
    private final String message;

    SecondaryEnum(int id, String message) {
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
