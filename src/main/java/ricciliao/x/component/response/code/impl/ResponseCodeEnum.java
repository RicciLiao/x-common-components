package ricciliao.x.component.response.code.impl;

import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.ResponseCode;
import ricciliao.x.component.response.code.Secondary;

public enum ResponseCodeEnum implements ResponseCode {
    SUCCESS(PrimaryEnum.SUCCESS, SecondaryEnum.BLANK),
    UNEXPECTED_ERROR(PrimaryEnum.UNEXPECTED_ERROR, SecondaryEnum.BLANK),
    ;

    private final Primary primary;
    private final Secondary secondary;

    ResponseCodeEnum(Primary primary, Secondary secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    @Override
    public Primary getPrimary() {

        return this.primary;
    }

    @Override
    public Secondary getSecondary() {

        return this.secondary;
    }
}
