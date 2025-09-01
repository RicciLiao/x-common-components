package ricciliao.x.component.utils;

import jakarta.annotation.Nonnull;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.props.CommonProperties;
import ricciliao.x.component.response.data.SimpleData;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public static LocalDateTime toLocalDateTime(Long milliseconds) {

        return Objects.isNull(milliseconds) ? null : toLocalDateTimeNotNull(milliseconds);
    }

    public static LocalDateTime toLocalDateTimeNotNull(long milliseconds) {

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), TIME_ZONE.toZoneId());
    }

    public static Long toLong(LocalDateTime localDateTime) {

        return Objects.isNull(localDateTime) ? null : toLongNotNull(localDateTime);
    }

    public static LocalDate toLocalDate(Long milliseconds) {

        return Objects.isNull(milliseconds) ? null : toLocalDateNotNull(milliseconds);
    }

    public static Long toLong(LocalDate localDate) {

        return Objects.isNull(localDate) ? null : CoreUtils.toLong(localDate.atTime(LocalTime.MIN));
    }

    public static LocalDate toLocalDateNotNull(long milliseconds) {

        return CoreUtils.toLocalDateTimeNotNull(milliseconds).toLocalDate();
    }

    public static long toLongNotNull(@Nonnull LocalDateTime localDateTime) {

        return localDateTime.atZone(TIME_ZONE.toZoneId()).toInstant().toEpochMilli();
    }

    public static long toLongNotNull(@Nonnull LocalDate localDate) {

        return CoreUtils.toLongNotNull(localDate.atTime(LocalTime.MIN));
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
