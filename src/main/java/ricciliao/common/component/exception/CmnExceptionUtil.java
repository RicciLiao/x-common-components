package ricciliao.common.component.exception;


import org.apache.commons.lang3.StringUtils;

public class CmnExceptionUtil {

    private CmnExceptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String stackTraceToString(Throwable t) {
        StringBuilder sbr = new StringBuilder();
        traceStack(sbr, t);
        return sbr.toString();
    }

    private static void traceStack(StringBuilder sbr, Throwable throwable) {
        if (throwable instanceof ServiceException
                && ((ServiceException) throwable).getThrowable() != null) {
            traceStack(sbr, ((ServiceException) throwable).getThrowable());
        }
        sbr.append(throwable.getClass().getName()).append(":");
        if (StringUtils.isNotBlank(throwable.getMessage())) {
            sbr.append(enhanceExceptionMessage(throwable));
        }
        sbr.append(System.lineSeparator());
        for (StackTraceElement stackTraceElement : throwable.getStackTrace()) {
            sbr.append("\tat ").append(stackTraceElement.toString()).append(System.lineSeparator());
        }
    }

    private static String enhanceExceptionMessage(Throwable throwable) {
        String original = throwable.getMessage();
        /*if (throwable instanceof RestClientException
                && original.toLowerCase().matches(AlsConstant.ALS_REST_CLIENT_EXCEPTION_MESSAGE_REGX)) {
            String[] arr = original.split(" ");
            HttpStatus httpStatus = HttpStatus.resolve(Integer.parseInt(arr[0]));
            return httpStatus.value() + " " + httpStatus.getReasonPhrase();
        }*/

        return original;
    }

}
