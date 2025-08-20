package ricciliao.x.component.response.code.impl;

import ricciliao.x.component.response.code.Primary;

public enum PrimaryEnum implements Primary {

    SUCCESS(0, "SUCCESS"),
    SECURITY_ERROR(1, ""),
    PARAMETER_ERROR(2, ""),
    CONCURRENT_ERROR(3, ""),
    REST_ERROR(4, ""),
    DATA_ERROR(5, ""),
    UNEXPECTED_ERROR(9, "System error."),
    ;

    private final int id;
    private final String message;

    PrimaryEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
