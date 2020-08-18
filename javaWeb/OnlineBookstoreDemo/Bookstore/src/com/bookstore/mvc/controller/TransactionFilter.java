package com.bookstore.mvc.controller;

import com.bookstore.mvc.model.DAOutils.Utils;
import com.bookstore.mvc.model.connection.ConnectContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebFilter(filterName = "TransactionFilter")
public class TransactionFilter implements Filter {
    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain){
        Connection connect = null;
        try {
            // 获取连接
            connect = Utils.getConnection();
            // 开启事务, 取消自动提交
            connect.setAutoCommit(false);
            //利用ThreadLocal把连接和当前线程绑定
            ConnectContext.getInstance().bind(connect);

            //把请求传给Servlet
            chain.doFilter(req, resp);
            // 提交事务
            connect.commit();

        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            try {
                if (connect != null)
                    connect.rollback();
                // 跳转页面
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect(request.getContextPath()+"/error-1.html");

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }finally{
            // 解除绑定
            ConnectContext.getInstance().remove();
            // 关闭连接
            Utils.closeResource(connect);
        }
    }

    public void init(FilterConfig config){}

}
