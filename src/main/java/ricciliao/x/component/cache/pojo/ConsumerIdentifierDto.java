package ricciliao.x.component.cache.pojo;

import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ConsumerIdentifierDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -6723685925978483107L;

    public ConsumerIdentifierDto(String consumer, String store) {
        this.consumer = consumer;
        this.store = store;
    }

    public ConsumerIdentifierDto() {
    }

    private String consumer;
    private String store;

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public int hashCode() {
        return Objects.hash(consumer, store);
    }

    @Override
    public String toString() {

        return StringUtils.lowerCase(consumer + "_" + store);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ConsumerIdentifierDto that)) return false;

        return Objects.equals(this.toString(), that.toString());
    }

}
