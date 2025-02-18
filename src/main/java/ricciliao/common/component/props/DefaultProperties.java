package ricciliao.common.component.props;

public class DefaultProperties extends ApplicationProperties {

    public DefaultProperties() {
        this.timeZone = this.getYamlProperties().getProperty("time-zone", String.class);
    }

    private final String timeZone;

    public String getTimeZone() {
        return timeZone;
    }

}
