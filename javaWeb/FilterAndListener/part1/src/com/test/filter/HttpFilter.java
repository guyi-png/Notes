package com.test.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的HttpFilter,实现Filter接口
 */
public abstract class HttpFilter implements Filter {
    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        init();
    }

    protected void init() {}


    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        doFilter(request, response, filterChain);
    }

    /**
     * 子类继承只需重写此方法就行
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public abstract void doFilter
            (HttpServletRequest request, HttpServletResponse response,FilterChain chain)
            throws IOException, ServletException;

    @Override
    public void destroy() {}
}
