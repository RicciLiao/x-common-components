package ricciliao.x.component.response.code;

import java.io.Serializable;

public interface ResponseCode extends Serializable {

    Primary getPrimary();

    Secondary getSecondary();

}
