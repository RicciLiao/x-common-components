package ricciliao.x.component.utils;

import jakarta.annotation.Nonnull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.persistence.AuditAction;
import ricciliao.x.component.persistence.AuditSupport;
import ricciliao.x.component.persistence.LogAction;
import ricciliao.x.component.persistence.LogSupport;
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

    public static <T extends AuditSupport, R extends AuditSupport> R convert(@Nonnull T t, @Nonnull R r, @Nonnull AuditAction.Op<R> op) {
        r.setCreatedBy(t.getCreatedBy());
        r.setCreatedDtm(t.getCreatedDtm());
        r.setUpdatedBy(t.getUpdatedBy());
        r.setUpdatedDtm(t.getUpdatedDtm());
        r.setVersion(t.getVersion());

        return op.apply(r);
    }

    public static <T extends AuditSupport, R extends LogSupport> R convert(@Nonnull T t, @Nonnull R r, @Nonnull LogAction.Op<R> op) {
        r.setCreatedBy(t.getCreatedBy());
        r.setCreatedDtm(t.getCreatedDtm());
        r.setUpdatedBy(t.getUpdatedBy());
        r.setUpdatedDtm(t.getUpdatedDtm());
        r.setVersion(t.getVersion());
        r.setActionBy(t.getUpdatedBy());
        r.setActionDtm(t.getUpdatedDtm());

        return op.apply(r);
    }

}
