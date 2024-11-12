package ricciliao.common.component.response;

import java.io.Serializable;

public interface ResponseCode extends Serializable {

    long getId();

    String getMessage();

}
