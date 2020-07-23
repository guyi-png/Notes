<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>  <%--指明该页面为错误页面--%>
<html>
<head>
    <title>error</title>
    <style>
        h1{
            width: 400px;
            margin: 300px auto 0;
            font-size: 30px;
            color: chocolate;
        }
        div{
            width: 400px;
            margin: 0 auto ;
            font-size: 30px;
            color: chocolate;
        }
    </style>
</head>
<body>
<%--
客户直接访问会报空指针异常(exception.xx)，避免访问给将error.jsp文件放入WEB-INF中，
WEB-INF是不能被客户通过浏览器访问的
<%@ page errorPage="WEB-INF/error.jsp" %> 指定后会通过请求的转发的形式改变页面到error.jsp资源中显示
--%>
    <h1>很抱歉，出现了亿点错误</h1>
    <div>
        Error  message:
        <%=
           exception.getMessage()
        %>
    </div>
</body>
</html>
