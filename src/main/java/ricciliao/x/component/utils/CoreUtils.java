package ricciliao.x.component.utils;

import ricciliao.x.component.props.CommonProperties;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

public class CoreUtils {

    private CoreUtils() {
        throw new IllegalStateException("Utility class");
    }

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

    public static LocalDateTime toLocalDateTime(Long milliseconds) {

        return Objects.isNull(milliseconds) ? null : toLocalDateTime(milliseconds.longValue());
    }

    public static LocalDateTime toLocalDateTime(long milliseconds) {

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), TIME_ZONE.toZoneId());
    }

    public static Long toLong(LocalDateTime localDateTime) {

        return Objects.isNull(localDateTime) ? null : localDateTime.atZone(TIME_ZONE.toZoneId()).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(Long milliseconds) {

        return Objects.isNull(milliseconds) ? null : toLocalDate(milliseconds.longValue());
    }

    public static LocalDate toLocalDate(long milliseconds) {

        return CoreUtils.toLocalDateTime(milliseconds).toLocalDate();
    }

    public static Long toLong(LocalDate localDate) {

        return Objects.isNull(localDate) ? null : CoreUtils.toLong(localDate.atTime(LocalTime.MIN));
    }

}
