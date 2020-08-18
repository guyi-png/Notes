<%--
  User: 古乂
  Date: 2020/8/11
  Time: 11:46
  Content: 处理登录信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
    <%
        // 获取用户信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 信息完整，则把登录信息放到HttpSession,重定向到list.jsp
        if (username != null && username.trim().length()>0){
            if (password != null && password.trim().length()>0){
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                response.sendRedirect("list.html");
            }
        }

    %>
    <p>木大</p>
</body>
</html>
