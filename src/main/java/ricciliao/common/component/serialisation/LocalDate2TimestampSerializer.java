package ricciliao.common.component.serialisation;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ricciliao.common.component.utils.CommonHelper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class LocalDate2TimestampSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Long timestamp = CommonHelper.toLong(localDate);
        if (Objects.nonNull(timestamp)) {
            jsonGenerator.writeNumber(timestamp);
        }
    }

    @Override
    public Class<LocalDate> handledType() {

        return LocalDate.class;
    }

}
