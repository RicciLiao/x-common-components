package ricciliao.x.component.response.code.impl;


import ricciliao.x.component.response.code.PrimaryCode;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.code.SecondaryCode;

public enum ResponseCodeEnum implements ResponseCode {
    SUCCESS(PrimaryCodeEnum.SUCCESS, SecondaryCodeEnum.BLANK),
    SECURITY_ERROR(PrimaryCodeEnum.SECURITY_ERROR, SecondaryCodeEnum.BLANK),
    PARAMETER_ERROR(PrimaryCodeEnum.PARAMETER_ERROR, SecondaryCodeEnum.BLANK),
    CONCURRENT_ERROR(PrimaryCodeEnum.CONCURRENT_ERROR, SecondaryCodeEnum.BLANK),
    REST_ERROR(PrimaryCodeEnum.REST_ERROR, SecondaryCodeEnum.BLANK),
    DATA_ERROR(PrimaryCodeEnum.DATA_ERROR, SecondaryCodeEnum.BLANK),
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
