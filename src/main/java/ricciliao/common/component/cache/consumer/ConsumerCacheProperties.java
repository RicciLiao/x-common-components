package ricciliao.common.component.cache.consumer;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import ricciliao.common.component.rest.RestPathProperties;

@ConfigurationProperties(prefix = "cache-consumer")
public class ConsumerCacheProperties {

    private String consumer;
    private OperationProperties operation = new OperationProperties();

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public OperationProperties getOperation() {
        return operation;
    }

    public void setOperation(OperationProperties operation) {
        this.operation = operation;
    }

    public static class OperationProperties {

        private CacheRestPathProperties create = new CacheRestPathProperties("http://localhost:8083/operation", HttpMethod.POST);
        private CacheRestPathProperties update = new CacheRestPathProperties("http://localhost:8083/operation", HttpMethod.PUT);
        private CacheRestPathProperties delete = new CacheRestPathProperties("http://localhost:8083/operation/{id}", HttpMethod.DELETE);
        private CacheRestPathProperties retrieve = new CacheRestPathProperties("http://localhost:8083/operation/{id}", HttpMethod.GET);

        public CacheRestPathProperties getCreate() {
            return create;
        }

        public void setCreate(CacheRestPathProperties create) {
            this.create = create;
        }

        public CacheRestPathProperties getUpdate() {
            return update;
        }

        public void setUpdate(CacheRestPathProperties update) {
            this.update = update;
        }

        public CacheRestPathProperties getDelete() {
            return delete;
        }

        public void setDelete(CacheRestPathProperties delete) {
            this.delete = delete;
        }

        public CacheRestPathProperties getRetrieve() {
            return retrieve;
        }

        public void setRetrieve(CacheRestPathProperties retrieve) {
            this.retrieve = retrieve;
        }

        public static class CacheRestPathProperties extends RestPathProperties {

            public CacheRestPathProperties(String path, HttpMethod method) {
                super();
                this.setPath(path);
                this.setMethod(method);
            }
        }
    }

}
