package ricciliao.common.component.executor;

import ricciliao.common.component.exception.ServiceException;

public class RetryResult<T> {

    private T result;
    private ServiceException serviceException;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ServiceException getServiceException() {
        return serviceException;
    }

    public void setServiceException(ServiceException serviceException) {
        this.serviceException = serviceException;
    }

    public void clear() {
        this.result = null;
        this.serviceException = null;
    }
}
