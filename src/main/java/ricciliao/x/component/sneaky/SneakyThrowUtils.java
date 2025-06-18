package ricciliao.x.component.sneaky;

public class SneakyThrowUtils {

    public static void run(SneakyRunnable runnable) {
        try {
            runnable.run();
        } catch (Throwable e) {

            sneakyThrow(e);
        }
    }

    public static <T> T get(SneakySupplier<T> supplier) {
        try {

            return supplier.get();
        } catch (Throwable e) {

            return sneakyThrow(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable, R> R sneakyThrow(Throwable e) throws T {

        throw (T) e;
    }

    @FunctionalInterface
    public interface SneakySupplier<R> {
        R get() throws Throwable;   //NOSONAR
    }

    @FunctionalInterface
    public interface SneakyRunnable {
        void run() throws Throwable;    //NOSONAR
    }

}
