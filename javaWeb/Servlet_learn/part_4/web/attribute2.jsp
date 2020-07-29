<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>attribute2</title>
</head>
<body>
<label>
    pageContextAttr:
    <%=
    pageContext.getAttribute("pageContextAttr")
    %>
</label>
<label>
    requestAttr:
    <%=
    request.getAttribute("requestAttr")
    %>
</label>
<label>
    session:
    <%=
    session.getAttribute("session")
    %>
</label>
<label>
    application:
    <%=
    application.getAttribute("application")
    %>
</label>
</body>
</html>
