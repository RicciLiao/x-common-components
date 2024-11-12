package ricciliao.common.component.response;

import java.io.Serial;

public class ResponseSimpleData implements ResponseData {

    @Serial
    private static final long serialVersionUID = 2176789878890617628L;

    public record BooleanResult(boolean result) implements ResponseData {
    }

}
