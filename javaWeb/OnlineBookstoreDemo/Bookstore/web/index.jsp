
<%--
  User: 古乂
  Date: 2020/8/14
  Time: 9:42
  Content: 重定向到书城首页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <%
      response.sendRedirect(request.getContextPath()+"/booksServlet?method=getBooks");
    %>
  </body>
</html>
