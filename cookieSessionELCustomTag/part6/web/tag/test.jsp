<%--
  User: 古乂
  Date: 2020/7/28
  Time: 15:56
  Content: 使用自定义标签
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 导入标签库--%>
<%@ taglib prefix="olg" uri="http://www.test.com/hello/core" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>

<%--    <olg:hello value="${param.name}" count="10"/>--%>
    <br>
    <!--父把name打印到控制台-->
    <olg:parentTag>
        <!--子把父的name打印到页面-->
        <olg:sonTag/>
    </olg:parentTag>

    <olg:choose>
        <olg:when test="${param.age>=80}">已埋</olg:when>
        <olg:when test="${param.age>=18}">已死</olg:when>
        <olg:otherwise>人生无憾</olg:otherwise>
    </olg:choose>
</body>
</html>
