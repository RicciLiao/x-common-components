package ricciliao.x.component.serialisation;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ricciliao.x.component.utils.CoreUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long timestamp = CoreUtils.toLong(localDate);
        if (Objects.nonNull(timestamp)) {
            jsonGenerator.writeNumber(timestamp);
        }
    }

    @Override
    public Class<LocalDate> handledType() {

        return LocalDate.class;
    }

}
