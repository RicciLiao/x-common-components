package ricciliao.x.component.payload.response.code.impl;


import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;

public enum ResponseCodeEnum implements ResponseCode {
    SUCCESS(PrimaryCodeEnum.SUCCESS, SecondaryCodeEnum.BLANK),
    DATA_WARNING(PrimaryCodeEnum.DATA_WARNING, SecondaryCodeEnum.BLANK),
    PARAMETER_WARNING(PrimaryCodeEnum.PARAMETER_WARNING, SecondaryCodeEnum.BLANK),
    REST_WARNING(PrimaryCodeEnum.REST_WARNING, SecondaryCodeEnum.BLANK),
    SECURITY_ERROR(PrimaryCodeEnum.SECURITY_ERROR, SecondaryCodeEnum.BLANK),
    UNEXPECTED_ERROR(PrimaryCodeEnum.UNEXPECTED_ERROR, SecondaryCodeEnum.BLANK),
    ;

    private final PrimaryCode primary;
    private final SecondaryCode secondary;

    ResponseCodeEnum(PrimaryCode primary, SecondaryCode secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    @Override
    public PrimaryCode getPrimary() {

        return this.primary;
    }

    @Override
    public SecondaryCode getSecondary() {

        return this.secondary;
    }
}
