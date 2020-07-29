<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<!-- response.encodeURL(String val1)或response.encodeRedirectURL(String var1)重写URL让其带上sessionid-->
    <form action=<%=response.encodeURL("hello.jsp")%> method="post">
        <label>
            username:
            <input type="text" name="username"
                   value="<%=session.getAttribute("username")==null? "":session.getAttribute("username") %>">
        </label>
        <input type="submit" value="提交">
    </form>
    <p>sessionid: <%=session.getId()%></p>
    <p>isNew: <%=session.isNew()%></p>
    <p>MaxInactiveInterval: <%=session.getMaxInactiveInterval()%></p>
    <p>CreationTime: <%=session.getCreationTime()%></p>
    <p>LastAccessedTime: <%=session.getLastAccessedTime()%></p>
</body>
</html>
