package com.guyi.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessStep1Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        req.setCharacterEncoding("UTF-8");
        //获取选中的book的信息
        String[] books = req.getParameterValues("book");
        // 把信息放入HttpSession
        req.getSession().setAttribute("books", books);
        // 重定向到shoppingcart/step2.jsp中
        resp.sendRedirect(req.getContextPath()+"/shoppingcart/step2.jsp");
    }
}
