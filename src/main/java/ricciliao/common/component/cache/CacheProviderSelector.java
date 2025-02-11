package ricciliao.common.component.cache;

import ricciliao.common.component.cache.pojo.CacheDto;
import ricciliao.common.component.cache.pojo.ConsumerIdentifierDto;

import java.util.HashMap;
import java.util.Map;

public class CacheProviderSelector {

    private final Map<ConsumerIdentifierDto, CacheProvider> cacheProviderMap;
    private final Map<ConsumerIdentifierDto, Class<? extends CacheDto>> cacheClass;

    public CacheProviderSelector() {
        this.cacheProviderMap = new HashMap<>();
        this.cacheClass = new HashMap<>();
    }

    public Map<ConsumerIdentifierDto, CacheProvider> getCacheProviderMap() {
        return cacheProviderMap;
    }

    public Map<ConsumerIdentifierDto, Class<? extends CacheDto>> getCacheClass() {
        return cacheClass;
    }

    public CacheProvider selectProvider(ConsumerIdentifierDto identifier) {
        if (getCacheProviderMap().containsKey(identifier)) {

            return getCacheProviderMap().get(identifier);
        }

        return null;
    }

    public Class<? extends CacheDto> getCacheClass(ConsumerIdentifierDto identifier) {
        if (getCacheClass().containsKey(identifier)) {

            return getCacheClass().get(identifier);
        }

        return null;
    }

}
