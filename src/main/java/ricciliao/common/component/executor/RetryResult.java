package ricciliao.common.component.executor;

import java.io.Serializable;

public class RetryResult<T> implements Serializable {

    private T result;
    private Exception exception;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public void clear() {
        this.result = null;
        this.exception = null;
    }
}
