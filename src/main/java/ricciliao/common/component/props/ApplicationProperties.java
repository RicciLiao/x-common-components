package ricciliao.common.component.props;

import org.springframework.core.io.ClassPathResource;

public class ApplicationProperties {

    private final YamlPropertiesReader yamlProperties;

    public ApplicationProperties() {
        yamlProperties = new YamlPropertiesReader(new ClassPathResource("application.yml"));
    }

    public YamlPropertiesReader getYamlProperties() {
        return yamlProperties;
    }

}
