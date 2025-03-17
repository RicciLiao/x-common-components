package ricciliao.x.component.props;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YamlPropertiesReader {

    private final Map<String, Object> properties;
    private final ObjectMapper objectMapper;

    public YamlPropertiesReader(Resource resources) {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.properties = readYaml(resources);
    }

    public YamlPropertiesReader() {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.properties = readYaml(new ClassPathResource("application.yml"));
    }

    public Map<String, Object> getProperties() {
        return new LinkedHashMap<>(properties);
    }

    public <T> T getProperty(String name, Class<T> tClass) {
        Object value = getNestedProperty(toCamelCase(name), properties);

        if (Objects.isNull(value)) {

            return null;
        }

        return objectMapper.convertValue(value, tClass);
    }

    private Map<String, Object> readYaml(Resource resource) {
        try {

            return convertToCamelCase(objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            }));

        } catch (IOException e) {

            throw new IllegalStateException("failed to read YAML file...", e);
        }
    }

    private Object getNestedProperty(String name, Map<String, Object> map) {
        String[] keys = name.split("\\.");
        Object current = map;

        for (String key : keys) {
            if (current instanceof Map) {
                current = ((Map<?, ?>) current).get(key);
            } else {

                return null;
            }
        }

        return current;
    }

    private String toCamelCase(String key) {
        Pattern pattern = Pattern.compile("-(.)");
        Matcher matcher = pattern.matcher(key);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    private Map<String, Object> convertToCamelCase(Map<String, Object> map) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String camelCaseKey = toCamelCase(entry.getKey());
            Object value = entry.getValue();

            if (value instanceof Map) {
                value = convertToCamelCase(objectMapper.convertValue(value, new TypeReference<>() {
                }));
            } else if (value instanceof List) {
                value = convertListToCamelCase((List<?>) value);
            }

            result.put(camelCaseKey, value);
        }

        return result;
    }

    private List<Object> convertListToCamelCase(List<?> list) {
        List<Object> result = new ArrayList<>();
        for (Object item : list) {
            if (item instanceof Map) {
                result.add(convertToCamelCase(objectMapper.convertValue(item, new TypeReference<>() {
                })));
            } else {
                result.add(item);
            }
        }

        return result;
    }

}
