
<%--
  User: 古乂
  Date: 2020/8/11
  Time: 12:44
  Content: 用户登录，权限获取对应的资源
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <ul>
      <li><a href="article1.jsp">to article1</a></li>
      <li><a href="article2.jsp">to article2</a></li>
      <li><a href="article3.jsp">to article3</a></li>
      <li><a href="article4.jsp">to article4</a></li>
    </ul>
    <p><a href="loginServlet?method=logout">注销</a></p>
    <p><a href="./authority-manager.jsp">权限管理</a></p>
  </body>
</html>
