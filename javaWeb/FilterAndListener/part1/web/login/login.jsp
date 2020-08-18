<%--
  User: 古乂
  Date: 2020/8/11
  Time: 11:43
  Content: 登录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
    <form action="dologin.jsp" method="post">
        <label>
            name:
            <input type="text" name="username">
        </label>
        <label>
            password:
            <input type="password" name="password">
        </label>
        <input type="submit" value="提交">
    </form>
</body>
</html>
