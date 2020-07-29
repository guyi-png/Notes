<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html lang="zh-CN">
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <h1>
        hello
    </h1>
<%--   <% %>之中可编写java代码     --%>
<%--   JSP本质上是Servlet --%>
<%--
    JSP的隐藏变量： 可在jsp中直接使用
        PageContext pageContext;
        HttpSession session;
        ServletContext application;
        ServletConfig config;
        JspWriter out;
        Object page;
        HttpServletRequest request;
        HttpServletResponse response;
--%>
  <%
    // 获取请求的参数
    request.getParameter("name");
    response.setContentType("text/plain");
    // pageContext：页面的上下文，可以从该对象中获取上面的隐藏对象，也可以从中获取当前页面的信息
    ServletRequest request1 = pageContext.getRequest();// 获取HttpServletRequest的对象
    System.out.println(request==request1); //true
    // session: 代表浏览器与服务器的一次会话
    System.out.println(session.getId());
    // application: 代表当前WEB应用
    System.out.println(application.getInitParameter("user"));
    // config：当前jsp对应的Servlet的servletConfig对象
    System.out.println(config.getInitParameter("name"));
    // out: 调用print()可以直接把字符串打印到浏览器
    out.print("奥利给");
    out.print("<br/>");
    // page: 指向当前jsp对应的Servlet对象的引用
    out.print(page);
    // 在jsp中加入  page isErrorPage="true" 才可以用exception
//    exception.getCause();
  %>
  </body>
</html>
