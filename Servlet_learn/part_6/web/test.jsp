<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>俺在呢</p>
    <%
        System.out.println("ok");
        // 访问包含该jsp的jsp文件的某个变量
//        String flag = request.getParameter("flag");
        String abc = request.getParameter("abc");
        System.out.println(flag);
        System.out.println(abc);
    %>
</body>
</html>
