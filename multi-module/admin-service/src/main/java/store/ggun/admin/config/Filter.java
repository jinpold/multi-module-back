package store.ggun.admin.config;
import jakarta.servlet.*;
import java.io.IOException;

public class Filter implements jakarta.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jakarta.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    }
    @Override
    public void destroy() {
        jakarta.servlet.Filter.super.destroy();
    }
}
