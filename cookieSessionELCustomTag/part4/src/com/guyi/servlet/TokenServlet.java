package com.guyi.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "TokenServlet")
public class TokenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        /*
        *   在提交表单到servlet时，而servlet通过转发的方式响应一个jsp页面，此时地址栏的地址还是
        *   servlet对应的路径，刷新浏览器页面时会再次提交表单，
        *   在响应页面没有到达时点击多次提交按钮时会多次提交表单
        *   在响应了页面后，点击返回“<-”然后没有点击刷新，又点击提交，也会重复提交表单
        *避免重复提交表单：
        *   在表单中做一个标记，提交到Servlet时，检查标记是否存在且和预定义的标记一致
        *   如果是一致的则受理请求并销毁标记，
        *   如果标记不存在或者标记不一致，则响应重复信息
        * */
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        Object token = session.getAttribute("token"); //获取表单标记
        String token1 = request.getParameter("token"); //为了更安全，用隐藏标签中的标记与上面的标记比较
        if (token1.equals(token)){
            session.removeAttribute("token");
        }else{
            response.sendRedirect(request.getContextPath()+"/token/repeat.jsp");
            return;
        }

        String name = request.getParameter("name");
        System.out.println(name);

        request.getRequestDispatcher("token/success.jsp").forward(request,response);
    }

}
