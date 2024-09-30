package ricciliao.common.component.executor;

@FunctionalInterface
public interface RetryJob<T, Z> {
    T executor(Z z) throws Throwable;

}
