package com.java.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContentFilter extends HttpFilter{
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // request没有setParameter()方法,所以对HttpServletRequest修饰包装
        HttpServletRequest req = new MyHttpServletRequest(request);
        // 过滤fuck，shit等词
        req.getParameter("content");

        // 放行
        chain.doFilter(req, response);
    }
}
