package ricciliao.x.component.utils;

import jakarta.annotation.Nonnull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.props.ApplicationProperties;
import ricciliao.x.component.props.YamlPropertiesReader;

import java.util.Collections;
import java.util.List;

public class CoreUtils {

    private CoreUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final YamlPropertiesReader yamlPropertiesReader = new YamlPropertiesReader();

    public static List<SimplePayloadData.FieldViolation> toFieldViolation(@Nonnull BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {

            return
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(fieldError ->
                                    SimplePayloadData.of(fieldError.getField(), fieldError.getDefaultMessage()))
                            .toList();
        }

        return Collections.emptyList();
    }

    public static <T extends ApplicationProperties> T convert2Properties(Class<T> propsClass) {
        ConfigurationProperties propsPrefix = propsClass.getAnnotation(ConfigurationProperties.class);

        return yamlPropertiesReader.getProperty(propsPrefix.value(), propsClass);
    }

}
