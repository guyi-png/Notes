<%--
  User: 古乂
  Date: 2020/8/11
  Time: 16:28
  Content: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
    <form action="loginServlet?method=login" method="post">
        <label>
            username:
            <input type="text" name="username">
        </label>
        <input type="submit" value="登录">
    </form>
</body>
</html>
