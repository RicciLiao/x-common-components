package ricciliao.x.component.rest.args;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ricciliao.x.component.rest.RestPathProperties;

import java.io.Serializable;

public interface RestQueryParams extends Serializable {

    default UriComponents toUriComponents(RestPathProperties props) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        this.toQueryParams(queryParams);

        UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(props.getPath());
        ucb.queryParams(queryParams);

        return ucb.build();
    }

    void toQueryParams(MultiValueMap<String, String> queryParams);

}
