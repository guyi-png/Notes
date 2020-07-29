<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  在servlet中--%>
    <a href="forward">click me to forward</a>
    <br>
    <a href="redirect">click me to redirect</a>
    <br/>
<%--    在jsp中--%>
    <a href="a.jsp">a.jsp->c.jsp</a>
    <a href="b.jsp">b.jsp=>c.jsp</a>
  </body>
</html>
