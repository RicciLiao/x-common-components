package ricciliao.x.component.payload.response.code.impl;


import ricciliao.x.component.payload.response.code.PrimaryCode;

public enum PrimaryCodeEnum implements PrimaryCode {

    SUCCESS(0, "Success"),
    SECURITY_ERROR(1, "Security error."),
    PARAMETER_ERROR(2, "Parameter error"),
    CONCURRENT_ERROR(3, "Concurrent error"),
    REST_ERROR(4, "Rest Call error"),
    DATA_ERROR(5, "Data error"),
    UNEXPECTED_ERROR(9, "Unexpected error."),
    ;

    private final int id;
    private final String message;

    PrimaryCodeEnum(int id, String message) {
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
