package com.test.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器实际上就是对web资源进行拦截，做一些处理后再交给下一个过滤器或servlet处理
 * 通常都是用来拦截request进行处理的，也可以对返回的response进行拦截处理
 *
 * 具体实现：
 *  创建一个实现Filter接口的类
 *  在web.xml文件中像Servlet一样的配置Filter
 *
 */
public class HelloFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 和Servlet中的init()差不多
        System.out.println("hello init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        /*
         * 此方法是Filter的核心
         * FilterChain： filter链，多个Filter可以构成一个Filter链
         * 多个Filter拦截的顺序和web.xml文件中<filter-mapping>配置的顺序有关
         */
        System.out.println("hello doFilter");
        // 放行，使用FilterChain的唯一方法，将请求传给Filter链的下一个Filter或者传给Servlet
        filterChain.doFilter(servletRequest,servletResponse);
        // chain.doFilter()执行后执行权给下一个Filter或Servlet，
        // 然后按这个顺序倒着执行(每个Filter在chain.doFilter()之后的代码)
        // doFilter()是回溯的执行
    }

    @Override
    public void destroy() {
        // 和Servlet中的destroy()差不多
        System.out.println("hello destroy");
    }
}
