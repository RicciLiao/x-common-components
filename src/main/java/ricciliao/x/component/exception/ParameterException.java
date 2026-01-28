package ricciliao.x.component.exception;


import jakarta.annotation.Nonnull;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.impl.PrimaryCodeEnum;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

public class ParameterException extends AbstractException {
    @Serial
    private static final long serialVersionUID = -6737562056794016208L;

    private final SimplePayloadData.Collection<SimplePayloadData.FieldViolation> collection;

    public ParameterException(@Nonnull SecondaryCode secondaryCode,
                              List<SimplePayloadData.FieldViolation> list) {
        super(secondaryCode);
        this.collection = SimplePayloadData.Collection.data(list);
    }

    public ParameterException(@Nonnull SecondaryCode secondaryCode) {
        super(secondaryCode);
        this.collection = SimplePayloadData.Collection.data(Collections.emptyList());
    }

    @Override
    public PrimaryCode getPrimaryCode() {

        return PrimaryCodeEnum.PARAMETER_ERROR;
    }

    public SimplePayloadData.Collection<SimplePayloadData.FieldViolation> getCollection() {
        return collection;
    }

    @Override
    public PayloadData getResponseData() {

        return this.getCollection();
    }
}
