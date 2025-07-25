package ricciliao.x.component.executor;


import ricciliao.x.component.exception.CmnServiceException;

public interface RetryExecutors {
    <T, Z> T executor(Z z, RetryJob<T, Z> restTask, RetrySelector<T> retrySelector, RetrySelector.RetryMeta retryMeta) throws CmnServiceException;
}
