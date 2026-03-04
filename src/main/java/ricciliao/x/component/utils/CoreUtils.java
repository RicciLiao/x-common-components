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

    private static final YamlPropertiesReader yamlPropertiesReader = new YamlPropertiesReader();

    private CoreUtils() {
        throw new IllegalStateException("Utility class");
    }

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

    public static <T extends ApplicationProperties> T convert2Properties(@Nonnull Class<T> propsClass) {
        ConfigurationProperties propsPrefix = propsClass.getAnnotation(ConfigurationProperties.class);

        return yamlPropertiesReader.getProperty(propsPrefix.value(), propsClass);
    }

    public char[] getAesKeyAsHexChars(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        char[] hexDigits = "0123456789abcdef".toCharArray();

        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexDigits[v >>> 4];
            hexChars[i * 2 + 1] = hexDigits[v & 0x0F];
        }

        return hexChars;
    }

}
