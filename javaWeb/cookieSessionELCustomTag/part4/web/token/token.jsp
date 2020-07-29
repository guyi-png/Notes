<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        // 表单的标记
        session.setAttribute("token", new Date().getTime()+"");
    %>
    <form action="<%=request.getContextPath()%>/tokenServlet" method="post">
    <%--   隐藏标签用于验证token--%>
        <input type="hidden" name="token" value="<%=session.getAttribute("token")%>">
        <label>name: <input type="text" name="name"></label>
        <input type="submit" value="提交">
    </form>
</body>
</html>
