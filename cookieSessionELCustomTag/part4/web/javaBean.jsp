<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在jsp中使用javaBean</title>
</head>
<body>
    <jsp:useBean id="customer" class="JavaBean" scope="session"/>  <!--创建对象传入scope里-->
    <jsp:setProperty property="name" value="olg" name="customer"/>  <!--设置对象的属性-->
    <jsp:setProperty property="name" value="*" name="customer"/>
    <jsp:getProperty property="name" name="customer"/>  <!--获取属性-->
</body>
</html>
