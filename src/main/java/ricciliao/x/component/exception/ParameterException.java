package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.PrimaryCode;
import ricciliao.x.component.response.code.SecondaryCode;
import ricciliao.x.component.response.code.impl.PrimaryCodeEnum;
import ricciliao.x.component.response.data.SimpleData;

import java.io.Serial;
import java.util.List;

public class ParameterException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -6737562056794016208L;

    private final SimpleData.Collection<SimpleData.FieldViolation> collection;

    protected ParameterException(@Nonnull SecondaryCode secondaryCode,
                                 List<SimpleData.FieldViolation> list) {
        super(secondaryCode);
        this.collection = SimpleData.Collection.data(list);
    }

    @Override
    PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.PARAMETER_ERROR;
    }

    public SimpleData.Collection<SimpleData.FieldViolation> getCollection() {
        return collection;
    }
}
