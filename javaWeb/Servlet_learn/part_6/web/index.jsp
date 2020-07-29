<%--
  jsp指令： page， include， taglib
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>  <%--这行就是jsp指令, contentType指定了jsp的响应类型和编码方式 --%>
<%@ page import="java.util.Date"  %>  <%-- jsp指令，导入包--%>
<%@ page session="false" %> <%--jsp指令，session默认为true，当为false时不能使用session --%>
<%@ page errorPage="WEB-INF/error.jsp" %>  <%--当错误时可以指定显示的页面--%>
<%@ page isELIgnored="true" %> <%--指定jsp页面是否可以使用EL表达式--%>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <h1>奥利给</h1>
    <%
      Date date = new Date();
      System.out.println(date);
    %>
  <%
     // int i = 100 / 0;
      String flag = "行";
  %>

<%--静态包含, 将当前jsp与下面指定的jsp文件的内容一同转为Servlet源文件--%>
  <%@ include file="/test.jsp" %>
<%--  在该文件中动态包含test.jsp, 将两个文件都转为两个Servlet源文件--%>
    <jsp:include page="test.jsp">
      <jsp:param name="flag" value="行"/>
    </jsp:include>

<%-- 以下两种方式等价--%>
    <%
      request.getRequestDispatcher("test.jsp");
    %>
    <jsp:forward page="test.jsp">
      <jsp:param name="abc" value="123"/>
    </jsp:forward>
  </body>
</html>
