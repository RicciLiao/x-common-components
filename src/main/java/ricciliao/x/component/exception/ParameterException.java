package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.response.code.Primary;
import ricciliao.x.component.response.code.Secondary;
import ricciliao.x.component.response.code.impl.PrimaryEnum;
import ricciliao.x.component.response.data.SimpleData;

import java.io.Serial;
import java.util.List;

public class ParameterException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -6737562056794016208L;

    private final SimpleData.Collection<SimpleData.FieldViolation> collection;

    protected ParameterException(@Nonnull Secondary secondaryCode,
                                 List<SimpleData.FieldViolation> list) {
        super(secondaryCode);
        this.collection = SimpleData.Collection.data(list);
    }

    @Override
    Primary getPrimaryCode() {

        return PrimaryEnum.PARAMETER_ERROR;
    }

    public SimpleData.Collection<SimpleData.FieldViolation> getCollection() {
        return collection;
    }
}
