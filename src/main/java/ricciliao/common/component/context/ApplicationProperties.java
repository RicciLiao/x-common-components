package ricciliao.common.component.context;

import org.springframework.core.io.ClassPathResource;

public abstract class ApplicationProperties {

    protected final YamlPropertiesReader yamlProperties;

    protected ApplicationProperties(YamlPropertiesReader yamlProperties) {
        this.yamlProperties = yamlProperties;
    }

    protected ApplicationProperties() {
        yamlProperties = new YamlPropertiesReader(new ClassPathResource("application.yml"));
    }
}
