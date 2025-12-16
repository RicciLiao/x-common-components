package ricciliao.x.component.props;

public class CommonProperties extends ApplicationProperties {

    /**
     * Application unique name, will retrieve from BuildProperties
     */
    private String consumer;

    private String cryptoPassword;

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getConsumer() {
        return consumer;
    }

    public String getCryptoPassword() {
        return cryptoPassword;
    }

    public void setCryptoPassword(String cryptoPassword) {
        this.cryptoPassword = cryptoPassword;
    }

}
