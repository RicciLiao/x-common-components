package ricciliao.common.component.executor;


public interface RetryExecutors {
    <T, Z> T executor(Z z, RetryJob<T, Z> restTask, RetrySelector<T> retrySelector, RetrySelector.RetryMeta retryMeta) throws Exception;
}
