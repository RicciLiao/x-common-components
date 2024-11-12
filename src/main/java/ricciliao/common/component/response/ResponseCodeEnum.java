package ricciliao.common.component.response;

public enum ResponseCodeEnum implements ResponseCode {

    SUCCESS(0, "Success."),
    TOKEN_EXPIRE_ERROR(1, "Token has expired, please log in again."),
    TOKEN_INVALID_ERROR(2, "User or password is invalid!"),
    PARAMETER_ERROR(3, "Invalid parameter!"),
    VERSION_ERROR(4, "Updated by others."),
    SYSTEM_ERROR(5, "System error."),
    REST_ERROR(6, "Fail to call '%s'. Case by: %s"),
    ;

    private final long id;
    private final String message;

    ResponseCodeEnum(long code, String message) {
        this.id = code;
        this.message = message;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
