<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/23
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>attribute1</title>
</head>
<body>
    <%
        // 四个域对象
        /**
         * pageContext: 属性的作用范围仅限于当前jsp页面
         * request： 属性的作用范围仅限于同一个请求
         * session： 属性的作用范围仅限于一次会话(浏览器打开到关闭)
         * application: 属性的作用范围限于当前WEB应用
         */
        pageContext.setAttribute("pageContextAttr", 1);
        request.setAttribute("requestAttr",2);
        session.setAttribute("session", 3);
        application.setAttribute("application", 4);
    %>
    <label>
        pageContextAttr:
        <%=
           pageContext.getAttribute("pageContextAttr")
        %>
    </label>
    <label>
        requestAttr:
        <%=
            request.getAttribute("requestAttr")
        %>
    </label>
    <label>
        session:
        <%=
            session.getAttribute("session")
        %>
    </label>
    <label>
        application:
        <%=
            application.getAttribute("application")
        %>
    </label>
    <a href="./attribute2.jsp">click me</a>
</body>
</html>
