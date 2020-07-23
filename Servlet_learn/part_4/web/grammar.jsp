<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>

    <%
        Date date = new Date();
        out.print(date);
    %>
<%--    上下等价--%>
<%--    jsp表达式--%>
    <%=
         date
    %>

<%-- jsp脚本片段--%>
    <%
        int number  = (int) (Math.random()*100);
        if (number < 50){
    %>
    小
    <%
        }else{
    %>
    大
    <%
        }
    %>

<%--  由于jsp代码被翻译到了_jspService方法中，jsp声明可以使声明的方法放到_jspService外--%>
    <%!
       void test(){};
    %>

<!-- 这是html注释-->  <%-- 这是jsp注释--%>


</body>
</html>
