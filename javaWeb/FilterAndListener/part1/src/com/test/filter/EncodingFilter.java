package com.test.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 从web.xml中读取encoding参数配置, 设置编码
 */
public class EncodingFilter extends HttpFilter{
    private String encoding;

    @Override
    protected void init() {
        encoding = getFilterConfig().getServletContext().getInitParameter("encoding");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 设置编码
        request.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }
}
