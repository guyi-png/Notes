<%@ page import="com.java.listener.Customer" %>
<%--
  User: 古乂
  Date: 2020/8/12
  Time: 11:12
  Content: servlet监听器

  监听器： 专门用于对其他对象身上发生的事件或状态改变进行监听和响应处理的对象，
  当被监听的对象发生情况时，立即采取相应的行为
  Servlet监听器： servlet规范中定义的一种特殊类，它用于监听web应用程序中的ServletContext，
  HttpSession，Servlet Request等域对象的创建和销毁事件，以及监听这些域对象中的属性发生修改的事件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <%
      request.setAttribute("name", "olg");
      System.out.println("&&&&&&&&&");
      request.setAttribute("name", "glo");
      System.out.println("^^^^^^^^^^^^");
      request.removeAttribute("name");
    %>
  <%
    Customer customer = new Customer();
    session.setAttribute("customer", customer);
//    session.removeAttribute("customer");
  %>
  </body>
</html>
