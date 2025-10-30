package ricciliao.x.component.executor.impl;


import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.executor.RetryExecutors;
import ricciliao.x.component.executor.RetryJob;
import ricciliao.x.component.executor.RetryResult;
import ricciliao.x.component.executor.RetrySelector;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.util.Objects;

public class RetryExecutorsImpl implements RetryExecutors {

    @Override
    public <T, Z> T executor(Z z, RetryJob<T, Z> restTask, RetrySelector<T> retrySelector, RetrySelector.RetryMeta retryMeta) throws UnexpectedException {
        RetryResult<T> retryResult = new RetryResult<>();
        while (true) {
            try {
                retryResult.clear();
                retryResult.setResult(restTask.executor(z));
            } catch (Exception e) {
                retryResult.setException(new UnexpectedException(SecondaryCodeEnum.BLANK, e));
            }
            if (Objects.isNull(retrySelector) || !retrySelector.retry(retrySelector, retryResult, retryMeta)) {
                if (Objects.nonNull(retryResult.getException())) {

                    throw retryResult.getException();
                }

                return retryResult.getResult();
            }
        }
    }

}
