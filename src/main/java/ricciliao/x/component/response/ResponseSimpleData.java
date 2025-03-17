package ricciliao.x.component.response;

public interface ResponseSimpleData extends ResponseData {

    record Bool(boolean result) implements ResponseSimpleData {
    }

}
