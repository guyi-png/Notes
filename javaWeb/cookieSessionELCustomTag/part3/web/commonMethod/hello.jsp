<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>
    <p>sessionid: <%=session.getId()%></p>
    <p>isNew: <%=session.isNew()%></p>
    <p>MaxInactiveInterval: <%=session.getMaxInactiveInterval()%></p>
    <p>CreationTime: <%=session.getCreationTime()%></p>
    <p>LastAccessedTime: <%=session.getLastAccessedTime()%></p>
    <label>
        Hello, <%=request.getParameter("username")%>
    </label>
    <%
        session.setAttribute("username", request.getParameter("username"));
    %>
    <a href="<%=response.encodeURL("login.jsp")%>">重新登录</a>
    <a href="<%=response.encodeURL("logout.jsp")%>">退出</a>

</body>
</html>
