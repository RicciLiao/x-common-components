package ricciliao.common.component.cache.provider;

import ricciliao.common.component.cache.pojo.ConsumerOperationDto;
import ricciliao.common.component.cache.pojo.CacheDto;

import java.time.Duration;

public abstract class CacheProvider {

    private final Duration ttl;

    protected CacheProvider(Duration ttl) {
        this.ttl = ttl;
    }

    public Duration getTtl() {
        return ttl;
    }

    public abstract boolean create(ConsumerOperationDto<CacheDto> operation);

    public abstract boolean update(ConsumerOperationDto<CacheDto> operation);

    public abstract ConsumerOperationDto<CacheDto> get(String key);

    public abstract boolean delete(String key);

}
