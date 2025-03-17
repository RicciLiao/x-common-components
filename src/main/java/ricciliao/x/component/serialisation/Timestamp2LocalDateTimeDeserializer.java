package ricciliao.x.component.serialisation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ricciliao.x.component.utils.CoreUtils;

import java.io.IOException;
import java.time.LocalDateTime;

public class Timestamp2LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        return CoreUtils.toLocalDateTime(jsonParser.readValueAs(Long.class));
    }

    @Override
    public Class<LocalDateTime> handledType() {

        return LocalDateTime.class;
    }

}
