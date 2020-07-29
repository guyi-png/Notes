<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注销</title>
</head>
<body>
    <p>sessionid: <%=session.getId()%></p>
    <p>isNew: <%=session.isNew()%></p>
    <p>MaxInactiveInterval: <%=session.getMaxInactiveInterval()%></p>
    <p>CreationTime: <%=session.getCreationTime()%></p>
    <p>LastAccessedTime: <%=session.getLastAccessedTime()%></p>
    <p>Bye, <%=session.getAttribute("username")==null ? "":session.getAttribute("username")%></p>
    <%
        session.invalidate();
    %>
    <a href="login.jsp">回到登录</a>
</body>
</html>
