package ricciliao.common.component.context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.Resource;

import java.util.Objects;
import java.util.Properties;

public class YamlPropertiesReader {

    private final Properties properties;
    private final ObjectMapper objectMapper;

    public YamlPropertiesReader(Resource... resources) {
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(resources);
        this.properties = yamlPropertiesFactoryBean.getObject();
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T getProperty(String name, Class<T> tClass) {
        Object value = this.properties.getProperty(name);
        if (Objects.isNull(value)) {

            return null;
        }

        return objectMapper.convertValue(value, tClass);
    }

}
