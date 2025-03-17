package ricciliao.x.component.executor.impl;


import ricciliao.x.component.exception.CmnServiceException;
import ricciliao.x.component.executor.RetryExecutors;
import ricciliao.x.component.executor.RetryJob;
import ricciliao.x.component.executor.RetryResult;
import ricciliao.x.component.executor.RetrySelector;

import java.util.Objects;

public class RetryExecutorsImpl implements RetryExecutors {

    @Override
    public <T, Z> T executor(Z z, RetryJob<T, Z> restTask, RetrySelector<T> retrySelector, RetrySelector.RetryMeta retryMeta) throws CmnServiceException {
        RetryResult<T> retryResult = new RetryResult<>();
        while (true) {
            try {
                retryResult.clear();
                retryResult.setResult(restTask.executor(z));
            } catch (Exception e) {
                retryResult.setServiceException(new CmnServiceException(e));
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
