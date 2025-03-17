package ricciliao.x.component.serialisation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ricciliao.x.component.utils.CoreUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class LocalDateTime2TimestampSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long timestamp = CoreUtils.toLong(localDateTime);
        if (Objects.nonNull(timestamp)) {
            jsonGenerator.writeNumber(timestamp);
        }
    }

    @Override
    public Class<LocalDateTime> handledType() {

        return LocalDateTime.class;
    }

}
