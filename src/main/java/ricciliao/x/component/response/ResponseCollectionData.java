package ricciliao.x.component.response;

import org.apache.commons.collections4.CollectionUtils;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public final class ResponseCollectionData implements ResponseData {
    @Serial
    private static final long serialVersionUID = -6157412652096644849L;

    public ResponseCollectionData(List<? extends ResponseData> result) {
        this.result = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(result)) {
            this.result.addAll(result);
        }
    }

    private final List<ResponseData> result;

    public List<ResponseData> getResult() {
        return result;
    }

    public static ResponseCollectionData data(List<? extends ResponseData> result) {

        return new ResponseCollectionData(result);
    }
}
