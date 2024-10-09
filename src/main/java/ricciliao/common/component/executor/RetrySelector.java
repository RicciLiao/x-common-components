package ricciliao.common.component.executor;

import java.util.Objects;

@FunctionalInterface
public interface RetrySelector<T> {

    boolean retry(RetryResult<T> r);

    default boolean retry(RetrySelector<T> retrySelector, RetryResult<T> retryResult, RetryMeta retryMeta) {

        if (Objects.nonNull(retryMeta)
                && retryMeta.attempt++ <= retryMeta.maxAttempts
                && retrySelector.retry(retryResult)) {
            try {
                Thread.sleep(retryMeta.interval);

                return true;
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
            }
        }

        return false;
    }

    class RetryMeta {
        public RetryMeta(int maxAttempts, int interval) {
            this.maxAttempts = maxAttempts;
            this.interval = interval;
            this.attempt = 1;
        }

        private int maxAttempts;
        private int interval;
        private int attempt;

        public int getMaxAttempts() {
            return maxAttempts;
        }

        public void setMaxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public int getAttempt() {
            return attempt;
        }

        public void setAttempt(int attempt) {
            this.attempt = attempt;
        }
    }
}
