package ricciliao.x.component.security;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;

public class SecurityStrategyRegistry {

    private static final List<SecurityProviderFactory> FACTORIES = new ArrayList<>();

    private static SecurityProvider cachedProvider;

    static {
        loadFactories();
    }

    private SecurityStrategyRegistry() {
        throw new IllegalStateException("Utility class");
    }

    private static void loadFactories() {
        FACTORIES.clear();
        ServiceLoader.load(SecurityProviderFactory.class)
                .stream()
                .filter(factory -> factory.get().active())
                .forEach(factory -> FACTORIES.add(factory.get()));
        FACTORIES.sort(Comparator.comparingInt(SecurityProviderFactory::getOrder));
        cachedProvider = FACTORIES.getFirst().createProvider();
    }

    public static SecurityProvider getOptimalProvider() {

        return cachedProvider;
    }

}
