package ricciliao.x.component.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ricciliao.x.component.props.CommonProperties;
import ricciliao.x.component.utils.CoreUtils;

import java.io.IOException;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public final class ResponseModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = -3818799819306161781L;

    public ResponseModule(CommonProperties props) {
        super(VersionUtil.parseVersion(props.getVersion(), "", "2"));
        this.addSerializer(LocalDate.class, new LocalDateSerializer());
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            return CoreUtils.toLocalDate(jsonParser.readValueAs(Long.class));
        }

        @Override
        public Class<?> handledType() {

            return LocalDate.class;
        }
    }

    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
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

    static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            return CoreUtils.toLocalDateTime(jsonParser.readValueAs(Long.class));
        }

        @Override
        public Class<LocalDateTime> handledType() {

            return LocalDateTime.class;
        }
    }

    static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
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

}
