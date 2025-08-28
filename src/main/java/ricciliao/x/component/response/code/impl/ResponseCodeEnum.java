package ricciliao.x.component.response.code.impl;


import ricciliao.x.component.response.code.PrimaryCode;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.code.SecondaryCode;

public enum ResponseCodeEnum implements ResponseCode {
    SUCCESS(PrimaryCodeEnum.SUCCESS, SecondaryCodeEnum.BLANK),
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
