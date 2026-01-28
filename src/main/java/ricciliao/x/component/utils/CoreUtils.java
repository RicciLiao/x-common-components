package ricciliao.x.component.utils;

import jakarta.annotation.Nonnull;
import org.springframework.validation.BindingResult;
import ricciliao.x.component.payload.SimplePayloadData;

import java.util.Collections;
import java.util.List;

public class CoreUtils {

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

}
