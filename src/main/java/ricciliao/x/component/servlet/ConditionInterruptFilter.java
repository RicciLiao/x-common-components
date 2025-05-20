package ricciliao.x.component.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

public abstract class ConditionInterruptFilter implements Filter {

    public abstract boolean doCondition(ServletRequest servletRequest, ServletResponse servletResponse);

    public abstract void doInterrupt(ServletRequest servletRequest, ServletResponse servletResponse);

    @Override
    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (this.doCondition(servletRequest, servletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            this.doInterrupt(servletRequest, servletResponse);
        }
    }

}
