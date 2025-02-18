package ricciliao.common.component.servlet;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class HttpServletWrapperFilter implements Filter {

    private final ExcludePathPatterns excludePathPatterns = new ExcludePathPatterns(
            Arrays.asList("/swagger-ui/.*", "/v3/api-docs/.*")
    );

    public ExcludePathPatterns getExcludePathPatterns() {
        return excludePathPatterns;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // init
    }

    public abstract boolean doFilter(ContentCachingRequestWrapper requestWrapper,
                                     ContentCachingResponseWrapper responseWrapper);

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper httpServletRequestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper httpServletResponseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        // Set default is UTF-8
        if (StringUtils.isBlank(httpServletRequest.getCharacterEncoding())) {
            httpServletRequestWrapper.setCharacterEncoding("UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
        }

        try {
            if (this.doFilter(httpServletRequestWrapper, httpServletResponseWrapper)) {
                chain.doFilter(httpServletRequestWrapper, httpServletResponseWrapper);
            }
        } finally {
            /**
             *@Description: Copy the complete cached body content to the response.
             */
            httpServletResponseWrapper.copyBodyToResponse();
        }
    }

    @Override
    public void destroy() {
        // destroy
    }

    public static class ExcludePathPatterns extends ArrayList<String> {

        @Serial
        private static final long serialVersionUID = 8274608574677831929L;

        public ExcludePathPatterns(List<String> list) {
            super(list);
        }

        public boolean matches(String url) {
            Iterator<String> iterator = iterator();
            if (StringUtils.isNotBlank(url)) {
                while (iterator.hasNext()) {
                    String excludePathPattern = iterator.next();
                    if (url.matches(excludePathPattern)) {

                        return true;
                    }
                }
            }

            return false;
        }
    }

}




























