package ricciliao.x.component.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.TimeZone;

@ConfigurationProperties("ricciliao.x.common")
public class CommonProperties extends ApplicationProperties {

    /**
     * com.fasterxml.jackson.databind.ObjectMapper TimeZone
     */
    private TimeZone timeZone;
    /**
     * Application unique name, will retrieve from spring.application.name
     */
    private String consumer;
    /**
     * Application version
     */
    private String version;

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
