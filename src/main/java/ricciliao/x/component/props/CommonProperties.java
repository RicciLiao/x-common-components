package ricciliao.x.component.props;

import java.util.TimeZone;

public class CommonProperties extends ApplicationProperties {

    /**
     * com.fasterxml.jackson.databind.ObjectMapper TimeZone
     */
    private TimeZone timeZone;
    /**
     * Application unique name, will retrieve from BuildProperties
     */
    private String consumer;
    /**
     * Application version
     */
    private String version;
    private String artifact;
    private String group;
    private String cryptoPassword;

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getConsumer() {
        return consumer;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCryptoPassword() {
        return cryptoPassword;
    }

    public void setCryptoPassword(String cryptoPassword) {
        this.cryptoPassword = cryptoPassword;
    }
}
