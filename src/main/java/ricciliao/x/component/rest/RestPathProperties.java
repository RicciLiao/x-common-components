package ricciliao.x.component.rest;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class RestPathProperties {

    private String path;
    private HttpMethodWrapper method;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethodWrapper getMethod() {
        return method;
    }

    public void setMethod(HttpMethodWrapper method) {
        this.method = new HttpMethodWrapper(method.name());
    }

    public HttpMethod getHttpMethod() {
        return method.httpMethod();
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.method = new HttpMethodWrapper(httpMethod.name());
    }

    public UriComponentsBuilder toBuilder() {

        return UriComponentsBuilder.fromHttpUrl(path);
    }

    public record HttpMethodWrapper(String name) {

        public HttpMethod httpMethod() {

            return HttpMethod.valueOf(this.name.toUpperCase());
        }
    }


}
