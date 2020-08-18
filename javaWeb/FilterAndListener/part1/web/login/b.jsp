<%--
  User: 古乂
  Date: 2020/8/11
  Time: 12:06
  Content: 登录可看内容
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
    <!--登录后可以看的内容-->
    <%
        // 检验用户是否登录，若没有登录就重定向到login.jsp
        Object username = session.getAttribute("username");
        Object password = session.getAttribute("password");

        if (username == null || password == null){
            response.sendRedirect("login.jsp");
        }
    %>

    <p>b页面</p>
    <a href="list.html">返回</a>
</body>
</html>
