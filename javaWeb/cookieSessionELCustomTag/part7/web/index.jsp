
<%--
  User: 古乂
  Date: 2020/7/29
  Time: 16:49
  Content: 使用el函数
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="https://www.test.com/func/core" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    ${fn:concat(param.a, param.b)}
  </body>
</html>
