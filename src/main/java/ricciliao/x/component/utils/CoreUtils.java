package ricciliao.x.component.utils;

import jakarta.annotation.Nonnull;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.payload.SimpleData;

import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

public class CoreUtils {

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("UTC");

    private CoreUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<SimpleData.FieldViolation> toFieldViolation(@Nonnull BindingResult bindingResult) {
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
