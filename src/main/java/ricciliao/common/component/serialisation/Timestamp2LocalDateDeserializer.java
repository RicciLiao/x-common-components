package ricciliao.common.component.serialisation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ricciliao.common.component.utils.CoreUtils;

import java.io.IOException;
import java.time.LocalDate;

public class Timestamp2LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        return CoreUtils.toLocalDate(jsonParser.readValueAs(Long.class));
    }

    @Override
    public Class<?> handledType() {

        return LocalDate.class;
    }

}
