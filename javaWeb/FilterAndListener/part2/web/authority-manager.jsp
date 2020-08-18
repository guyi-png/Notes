<%@ page import="java.util.List" %>
<%@ page import="com.java.authority.Authority" %>
<%@ page import="com.java.authority.User" %><%--
  User: 古乂
  Date: 2020/8/11
  Time: 13:40
  Content: 权限管理
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
    <style>
        div{
            position: absolute;
            left: 35%;
        }
    </style>
</head>
<body>
    <div>
        <form action="AuthorityServlet?method=getAuthorities" method="post">
            <label>
                name:
                <input type="text" name="username">
            </label>
            <input type="submit" value="获取用户权限信息">
        </form>
        <%
            List<Authority> authorities = (List<Authority>) request.getAttribute("authorities");
            User user = (User)request.getAttribute("user");
            String checked = "";
            if ( user != null){
        %>
            <p>${requestScope.user.username}的权限有：</p>
            <form action="AuthorityServlet?method=updateAuthority" method="post">
                <input type="hidden" name="username" value="${requestScope.user.username}">
                <%
                    for (Authority authority : authorities) {
                        if (user.getAuthorities().contains(authority)) {
                            checked = "checked";
                        }
                %>
                <label>
                    <input type="checkbox" name="authority"
                           value="<%=authority.getUrl()%>" <%=checked%>/>
                    <%=authority.getDisplayName()%>
                </label>
                <%
                        checked = "";
                    }
                %>
                <input type="submit" value="更新用户权限">
            </form>
        <%
            }
        %>
    </div>
    <p><a href="index.jsp">到首页</a></p>
</body>
</html>
