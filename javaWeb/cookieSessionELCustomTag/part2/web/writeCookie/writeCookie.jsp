<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/26
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    /*
            Cookieの作用范围： 可以作用当前目录和当前目录的子目录，
            但不能作用于当前目录的上一级目录
            此时writeCookie.jsp在writeCookie目录下，web目录不能获取cookie
            可以为cookie设置作用域
     */

%>
    <%
        // 向客户端写入cookiePath
        Cookie cookie= new Cookie("cookiePath","CookiePathValue");
        // 将cookie的作用域设为web根路径
        cookie.setPath(request.getContextPath());

        response.addCookie(cookie);
    %>
    <a href="../index.jsp">回首页</a>
</body>
</html>
