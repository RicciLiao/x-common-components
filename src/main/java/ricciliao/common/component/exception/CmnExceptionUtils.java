package ricciliao.common.component.exception;


import org.apache.commons.lang3.StringUtils;

public class CmnExceptionUtils {

    private CmnExceptionUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String stackTraceToString(Throwable t) {
        StringBuilder sbr = new StringBuilder();
        traceStack(sbr, t);
        return sbr.toString();
    }

    private static void traceStack(StringBuilder sbr, Throwable throwable) {
        if (throwable instanceof CmnServiceException se) {
            traceStack(sbr, se.getThrowable());
        }
        sbr.append(throwable.getClass().getName()).append(":");
        if (StringUtils.isNotBlank(throwable.getMessage())) {
            sbr.append(throwable.getMessage());
        }
        sbr.append(System.lineSeparator());
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            sbr.append("\tat ").append(stackTraceElement.toString()).append(System.lineSeparator());
        }
    }

}
