package ricciliao.x.component.payload.response.code.impl;


import ricciliao.x.component.payload.response.code.PrimaryCode;

public enum PrimaryCodeEnum implements PrimaryCode {

    SUCCESS(0, "Success."),
    DATA_WARNING(1, "Data warning."),
    PARAMETER_WARNING(2, "Parameter warning."),
    REST_WARNING(3, "Rest Call warning."),
    SECURITY_ERROR(8, "Security error."),
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
