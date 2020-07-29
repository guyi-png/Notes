<%@ page import="java.util.Arrays" %>
<%--
  Content: 学习cookie
--%>
<%--
  cookie是浏览器访问web服务器的某个资源时，由WEB服务器在http响应消息头中附带的一个小文本文件，
  一旦WEB浏览器保存了某个Cookie，那么它在以后每次访问该WEB服务器时，都会在HTTP请求头中将cookie回传给WEB服务器

--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page session="false" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <%
      // 在JavaWeb规范中使用Cookie类表示cookies
      //1.创建一个cookie对象
      Cookie cookie = new Cookie("name","olg");
      // 默认cookie是会话级别的，setMaxAge可以使其存储在磁盘上，
      // 表示存活单位为秒，设为0时浏览器会删除这个cookie,负数表示不存储
      cookie.setMaxAge(30);
      //2.通用response的一个方法把cookie传给客户端
      response.setCharacterEncoding("UTF-8");
      response.addCookie(cookie);
      //从request中获取cookies，并输出到浏览器
      Cookie[] cookies = request.getCookies();
      if (cookies != null && cookies.length>0){
        for (Cookie value : cookies){
            if ("name".equals(value.getName()))
            out.print(value.getName()+" : "+value.getValue());
        }
      }
    %>
    <h2><a href="hello.jsp">hello</a></h2>
  </body>
</html>
