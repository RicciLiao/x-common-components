package ricciliao.x.component.executor;

import ricciliao.x.component.exception.UnexpectedException;

public class RetryResult<T> {

    private T result;
    private UnexpectedException exception;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public UnexpectedException getException() {
        return exception;
    }

    public void setException(UnexpectedException exception) {
        this.exception = exception;
    }

    public void clear() {
        this.result = null;
        this.exception = null;
    }
}
