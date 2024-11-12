package ricciliao.common.component.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Objects;

public class CommonHelper {

    private CommonHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime toLocalDateTime(Long milliseconds) {

        return Objects.isNull(milliseconds) ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    public static Long toLong(LocalDateTime localDateTime) {

        return Objects.isNull(localDateTime) ? null : localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(Long milliseconds) {
        LocalDateTime localDateTime = CommonHelper.toLocalDateTime(milliseconds);

        return Objects.isNull(localDateTime) ? null : localDateTime.toLocalDate();
    }

    public static Long toLong(LocalDate localDate) {

        return Objects.isNull(localDate) ? null : CommonHelper.toLong(localDate.atTime(LocalTime.MIN));
    }

    public static Integer toInteger(Boolean b) {
        if (Objects.isNull(b)) {

            return null;
        }

        return Boolean.TRUE.equals(b) ? 1 : 0;
    }

    public static Boolean toBoolean(Integer integer) {
        if (Objects.isNull(integer)) {

            return null;//NOSONAR
        }

        return Integer.valueOf(1).equals(integer) ? Boolean.TRUE : Boolean.FALSE;
    }
}
