package ricciliao.x.component.utils;

import jakarta.annotation.Nullable;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.props.CommonProperties;
import ricciliao.x.component.response.data.SimpleData;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class CoreUtils {

    private static final TimeZone TIME_ZONE;

    static {
        TimeZone tz;
        try {
            CommonProperties commonProperties = SpringBeanUtils.getBean(CommonProperties.class);
            tz = commonProperties.getTimeZone();
            tz = Objects.isNull(tz) ? TimeZone.getTimeZone(ZoneId.systemDefault()) : tz;
        } catch (Exception e) {
            tz = TimeZone.getTimeZone(ZoneId.systemDefault());
        }
        TIME_ZONE = tz;
    }

    private CoreUtils() {
        throw new IllegalStateException("Utility class");
    }


    @Nullable
    public static LocalDateTime toLdt(@Nullable Instant instant) {
        if (Objects.isNull(instant)) {

            return null;
        }

        return LocalDateTime.ofInstant(instant, TIME_ZONE.toZoneId());
    }

    public static List<SimpleData.FieldViolation> toFieldViolation(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {

            return
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(fieldError ->
                                    SimpleData.of(fieldError.getField(), fieldError.getDefaultMessage()))
                            .toList();
        }

        return Collections.emptyList();
    }

}
