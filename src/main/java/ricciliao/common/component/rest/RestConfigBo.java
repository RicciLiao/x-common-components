package ricciliao.common.component.rest;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public record RestConfigBo(String path, HttpMethod method) {

    public UriComponentsBuilder toBuilder() {

        return UriComponentsBuilder.fromHttpUrl(path);
    }

}
