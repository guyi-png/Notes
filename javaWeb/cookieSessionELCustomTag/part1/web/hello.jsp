<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/26
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        if (name !=null && !"".equals(name.trim()) ){
            Cookie cookie = new Cookie("name", name);
            cookie.setMaxAge(10);
            response.addCookie(cookie);
        } else{
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length>0){
                for (Cookie value : cookies ){
                    if ("name".equals(value.getName()) ){
                        name = value.getValue();
                    }
                }
            }
        }
        if (name !=null && !"".equals(name.trim()) ){
            out.print("hello"+name);
        }else{
            response.sendRedirect("login.jsp");
        }
    %>
</body>
</html>
