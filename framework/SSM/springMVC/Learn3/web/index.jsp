<%--
  User: 古乂
  Date: 2020/8/25
  Time: 14:58
  Content: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
  <a href="${pageContext.servletContext.contextPath}/message">查看信息</a>
  <br>
  <a href="${pageContext.request.contextPath}/testExceptionHandlerExceptionResolver?x=10">
    Test ExceptionHandlerExceptionResolver
  </a>
  <br>
  <a href="${pageContext.request.contextPath}/testResponseStatusExceptionResolver?x=123">
    Test ResponseStatusExceptionResolver
  </a>
  <br>
  <a href="testSimpleMappingExceptionResolver?x=9">Test SimpleMappingExceptionResolver</a>
  </body>
</html>
