package ricciliao.x.component.security;

public interface SecurityProviderFactory {

    SecurityProvider createProvider();

    int getOrder();

    boolean active();

}
