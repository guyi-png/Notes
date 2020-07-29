<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        img{
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <form action="<%=request.getContextPath()%>/checkServlet" method="post">
        <label>name: <input type="text" name="name"></label>
        <label>验证码: <input type="text" name="CHECK_CODE_PARAM_NAME">
            <img src="<%=request.getContextPath()%>/validateCode" alt="验证码"></label>
        <input type="submit" value="提交">
    </form>
    <p><%=session.getAttribute("message")==null? "":session.getAttribute("message")%></p>
</body>
</html>
