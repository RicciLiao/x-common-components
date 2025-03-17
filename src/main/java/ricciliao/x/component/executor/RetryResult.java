package ricciliao.x.component.executor;

import ricciliao.x.component.exception.CmnServiceException;

public class RetryResult<T> {

    private T result;
    private CmnServiceException serviceException;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public CmnServiceException getServiceException() {
        return serviceException;
    }

    public void setServiceException(CmnServiceException serviceException) {
        this.serviceException = serviceException;
    }

    public void clear() {
        this.result = null;
        this.serviceException = null;
    }
}
