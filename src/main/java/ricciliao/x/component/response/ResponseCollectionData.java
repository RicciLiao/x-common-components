package ricciliao.x.component.response;

import java.util.List;

public record ResponseCollectionData(List<? extends ResponseData> result) implements ResponseData {

    public static ResponseCollectionData data(List<? extends ResponseData> result) {

        return new ResponseCollectionData(result);
    }
}
