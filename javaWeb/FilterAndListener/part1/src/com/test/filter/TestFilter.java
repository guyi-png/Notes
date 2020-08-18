package com.test.filter;

import javax.servlet.*;
import java.io.IOException;

public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("test init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // 拦截在HelloFilter之后
        System.out.println("test doFilter");
        // 请求的转发
        servletRequest.getRequestDispatcher("test.jsp")
                .forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("test destroy");
    }
}
