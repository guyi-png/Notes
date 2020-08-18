package com.bookstore.mvc.controller;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String encoding = filterConfig.getServletContext()
                .getInitParameter("encoding");
        servletRequest.setCharacterEncoding(encoding);
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
