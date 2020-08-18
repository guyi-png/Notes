package com.java.authority;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthorityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 获取servletPath
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        // 不需要登录就可以查看的url列表
        List<String> uncheckUrls = Arrays.asList("/403.jsp", "/index.jsp",
                "/authority-manager.jsp", "/login.jsp", "/logout.jsp");
        // 是uncheckUrls中的url直接可以显示
        if (uncheckUrls.contains(servletPath)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 用户是否登录，登录了获取用户信息
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        // 没有登录，重定向登录页面
        if (user == null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        // 登录了，查看权限是否允许查看资源
        // 比较url值
        List<Authority> authorities = user.getAuthorities();
        Authority authority  = new Authority(null, servletPath);
        // 有权限(contains方法内部使用了equals方法)
        if (authorities.contains(authority)){
            filterChain.doFilter(request, response);
            return;
        }
        // 没有权限,重定向403页面
        response.sendRedirect(request.getContextPath()+"/403.jsp");
    }

    @Override
    public void destroy() {

    }
}
