package ricciliao.common.component.rest;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

public class RestPathProperties {

    private String path;
    private HttpMethod method;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public UriComponentsBuilder toBuilder() {

        return UriComponentsBuilder.fromHttpUrl(path);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RestPathProperties) obj;
        return Objects.equals(this.path, that.path) &&
                Objects.equals(this.method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method);
    }

    @Override
    public String toString() {
        return "RestConfigBo[" +
                "path=" + path + ", " +
                "method=" + method + ']';
    }


}
