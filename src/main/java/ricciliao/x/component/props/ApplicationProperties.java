package ricciliao.x.component.props;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class ApplicationProperties {

    @JsonIgnore
    private final ObjectMapper objectMapper;

    protected ApplicationProperties() {
        objectMapper = new ObjectMapper();
    }

    public final <T> T getProperty(String name, Class<T> tClass) {
        Map<String, Object> map = objectMapper.convertValue(this, new TypeReference<>() {
        });
        if (map.containsKey(name)) {

            return objectMapper.convertValue(map.get(name), new TypeReference<T>() {
                @Override
                public Type getType() {
                    return tClass;
                }
            });
        }

        return null;
    }

}
