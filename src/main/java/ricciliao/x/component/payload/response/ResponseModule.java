package ricciliao.x.component.payload.response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ricciliao.x.component.props.CommonProperties;

import java.io.IOException;
import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

public final class ResponseModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = -3818799819306161781L;

    public ResponseModule(CommonProperties props) {
        super(VersionUtil.parseVersion(props.getVersion(), props.getGroup(), props.getArtifact()));
        this.addSerializer(Instant.class, new InstantSerializer());
        this.addDeserializer(Instant.class, new InstantDeserializer());
    }

    static class InstantDeserializer extends JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            return Instant.ofEpochMilli(jsonParser.readValueAs(Long.class));
        }

        @Override
        public Class<Instant> handledType() {

            return Instant.class;
        }
    }

    public static class InstantSerializer extends JsonSerializer<Instant> {
        @Override
        public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (Objects.nonNull(instant)) {
                jsonGenerator.writeNumber(instant.toEpochMilli());
            }
        }

        @Override
        public Class<Instant> handledType() {

            return Instant.class;
        }
    }

}
