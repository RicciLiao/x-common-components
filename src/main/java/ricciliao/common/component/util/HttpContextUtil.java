/*
package ricciliao.common.component.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import hk.health.medication.common.CommonHelper;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HttpContextUtil {

    private HttpContextUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    private static final String USER_AGENT = "user-agent";

    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if(ra == null){
            return null;
        } else {
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            return sra.getRequest();
        }
    }

    public static String getRemoteBrowser(HttpServletRequest servletRequest) {
        String ua = servletRequest.getHeader(USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        return browser != null ?
                new StringBuilder().append(browser.getName()).append(" ").append(browser.getVersion(ua)).toString() : "";
    }

    public static String getRemoteOs(HttpServletRequest servletRequest) {
        String ua = servletRequest.getHeader(USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        OperatingSystem os = userAgent.getOperatingSystem();
        return os != null ? os.getName() : "";
    }

    public static String getServiceHostname() {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            return "";
        }
    }

    public static String getRemoteIp(HttpServletRequest servletRequest) {
        if (CommonHelper.isBlank(servletRequest.getHeader(X_FORWARDED_FOR))) {
            return servletRequest.getRemoteAddr();
        } else if (servletRequest.getHeader(X_FORWARDED_FOR).indexOf(',') > -1) {
            return servletRequest.getHeader(X_FORWARDED_FOR)
                    .substring(0, servletRequest.getHeader(X_FORWARDED_FOR).indexOf(','));
        } else {
            return servletRequest.getHeader(X_FORWARDED_FOR);
        }
    }

}
*/
