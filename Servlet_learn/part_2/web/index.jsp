<%@ page import="com.guyi.test.Person" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/21
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>tight</title>
  </head>
  <body>
    <%
      Person person = new Person();
      System.out.println(person.get());
    %>
  </body>
</html>
