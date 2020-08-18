package com.java.authority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *  对用户权限的查看和修改
 */
public class AuthorityServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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

    public void getAuthorities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        User user = userDAO.get(username);
        List<Authority> authorities = userDAO.getAuthorities();
        request.setAttribute("user", user);
        request.setAttribute("authorities", authorities);
        request.getRequestDispatcher("/authority-manager.jsp").forward(request, response);
    }

    public void updateAuthority(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        String username = request.getParameter("username");
        // 获取选中的权限
        String[] urls = request.getParameterValues("authority");
        List<Authority> authorities = userDAO.getAuthorities(urls);
        // 更新用户的权限
        userDAO.update(username, authorities);

        response.sendRedirect(request.getContextPath()+"/authority-manager.jsp");
    }
}
