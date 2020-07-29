package com.guyi.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 从request中获取客户输入的验证码
        String checkCode = req.getParameter("CHECK_CODE_PARAM_NAME");
        // 从session中获取对应的验证码
        String code = (String)req.getSession().getAttribute("CHECK_CODE_KEY");
        // 验证
        if (checkCode.equalsIgnoreCase(code)){
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().print("验证码正确");
        }else{
            req.getSession().setAttribute("message", "验证码不一致");
            resp.sendRedirect(req.getContextPath()+"/check/check.jsp");
        }
    }
}
