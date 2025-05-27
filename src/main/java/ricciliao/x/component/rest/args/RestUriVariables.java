package ricciliao.x.component.rest.args;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ricciliao.x.component.rest.RestPathProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface RestUriVariables extends Serializable {

    default UriComponents toUriComponents(RestPathProperties props) {
        Map<String, Object> uriVariables = new HashMap<>();
        this.toUriVariables(uriVariables);

        UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(props.getPath());
        ucb.uriVariables(uriVariables);

        return ucb.build();
    }

    void toUriVariables(Map<String, Object> uriVariables);

}
