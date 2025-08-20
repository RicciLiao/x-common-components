package ricciliao.x.component.executor;

import ricciliao.x.component.exception.UnexpectedException;

public class RetryResult<T> {

    private T result;
    private UnexpectedException serviceException;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public UnexpectedException getServiceException() {
        return serviceException;
    }

    public void setServiceException(UnexpectedException serviceException) {
        this.serviceException = serviceException;
    }

    public void clear() {
        this.result = null;
        this.serviceException = null;
    }
}
