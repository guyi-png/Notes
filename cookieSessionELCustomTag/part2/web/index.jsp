<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/26
  Time: 18:47
  Content: 利用cookie，完成案例
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>购书</title>
  </head>
  <body>
    <h4>Books Page</h4>
    <a href="book.jsp?book=java">Java</a>
    <a href="book.jsp?book=html">html</a>
    <a href="book.jsp?book=js">javascript</a>
    <a href="book.jsp?book=php">php</a>
    <a href="book.jsp?book=c">c</a>
    <a href="book.jsp?book=python">python</a>
    <br>
    <br>
      <%
        // 获取所有cookie，然后显示信息
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length>0){
            for (Cookie value : cookies){
              if (!value.getName().startsWith("BOOK_")){
                continue;
              }
              out.print(value.getValue());
              out.print("<br>");
            }
        }
      %>

      <%
        //读取一个name为cookiePath的Cookie
        Cookie[] cookies1 = request.getCookies();
        if (cookies1 !=null && cookies1.length>0){
          for (Cookie value : cookies1){
            if ("cookiePath".equals(value.getName())){
              out.print(value.getName());
              out.print(value.getValue());
            }
          }
        }
      %>
  </body>
</html>
