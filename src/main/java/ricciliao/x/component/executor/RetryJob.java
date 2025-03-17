package ricciliao.x.component.executor;

@FunctionalInterface
public interface RetryJob<T, Z> {
    T executor(Z z);
}
