package ricciliao.x.component.servlet;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public abstract class ContentCachingFilter implements Filter {

    public abstract void doFilter(ContentCachingRequestWrapper requestWrapper,
                                  ContentCachingResponseWrapper responseWrapper,
                                  FilterChain chain) throws ServletException, IOException;

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper httpServletRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        try {
            this.doFilter(httpServletRequestWrapper, httpServletResponseWrapper, chain);
        } finally {
            httpServletResponseWrapper.copyBodyToResponse();
        }
    }

}




























