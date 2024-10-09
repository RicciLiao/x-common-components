package ricciliao.common.component.executor;


import ricciliao.common.component.exception.ServiceException;

public interface RetryExecutors {
    <T, Z> T executor(Z z, RetryJob<T, Z> restTask, RetrySelector<T> retrySelector, RetrySelector.RetryMeta retryMeta) throws ServiceException;
}
