package ricciliao.x.component.rest.args;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ricciliao.x.component.rest.RestPathProperties;

import java.util.HashMap;
import java.util.Map;

public interface RestArguments extends RestUriVariables, RestQueryParams {

    @Override
    default UriComponents toUriComponents(RestPathProperties props) {
        Map<String, Object> uriVariables = new HashMap<>();
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        this.toUriVariables(uriVariables);
        this.toQueryParams(queryParams);

        UriComponentsBuilder ucb = UriComponentsBuilder.fromHttpUrl(props.getPath());
        ucb.uriVariables(uriVariables);
        ucb.queryParams(queryParams);

        return ucb.build();
    }

}
