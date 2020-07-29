package com.guyi.servlet;

import com.guyi.servlet.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProcessStep2Servlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取name，address， cardType， cared
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String cardType = req.getParameter("cardType");
        String card = req.getParameter("card");
        Customer customer = new Customer(name, address, cardType, card);
        // 把信息放入HttpSession
        req.getSession().setAttribute("customer", customer);
        // 重定向页面到confirm.jsp
        resp.sendRedirect(req.getContextPath()+"/shoppingcart/confirm.jsp");
    }
}
