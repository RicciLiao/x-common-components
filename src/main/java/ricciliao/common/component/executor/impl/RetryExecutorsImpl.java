package ricciliao.common.component.executor.impl;


import ricciliao.common.component.exception.ServiceException;
import ricciliao.common.component.executor.RetryExecutors;
import ricciliao.common.component.executor.RetryJob;
import ricciliao.common.component.executor.RetryResult;
import ricciliao.common.component.executor.RetrySelector;

import java.util.Objects;

public class RetryExecutorsImpl implements RetryExecutors {

    @Override
    public <T, Z> T executor(Z z, RetryJob<T, Z> restTask, RetrySelector<T> retrySelector, RetrySelector.RetryMeta retryMeta) throws ServiceException {
        RetryResult<T> retryResult = new RetryResult<>();
        while (true) {
            try {
                retryResult.clear();
                retryResult.setResult(restTask.executor(z));
            } catch (Exception e) {
                retryResult.setServiceException(new ServiceException(e));
            }
            if (Objects.isNull(retrySelector) || !retrySelector.retry(retrySelector, retryResult, retryMeta)) {
                if (Objects.nonNull(retryResult.getServiceException())) {

                    throw retryResult.getServiceException();
                }

                return retryResult.getResult();
            }
        }
    }

}
