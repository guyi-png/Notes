package com.java.authority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 用户的登录和注销
 */
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("method");
        try {
            Method method = getClass().getMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        // 获取name
        String username = request.getParameter("username");
        // 调用userDAO获取用户的信息， 把用户信息放入HttpSession中
        User user = userDAO.get(username);
        request.getSession().setAttribute("user",user);
        // 重定向到index.jsp
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() +"/login.jsp");

    }
}
