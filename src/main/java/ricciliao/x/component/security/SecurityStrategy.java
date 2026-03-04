package ricciliao.x.component.security;

public interface SecurityStrategy {

    default SecurityProvider getSecurityProvider() {

        return SecurityStrategyRegistry.getOptimalProvider();
    }

}
