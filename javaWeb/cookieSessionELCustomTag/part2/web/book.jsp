<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/26
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买<%=request.getParameter("book")%></title>
</head>
<body>
    <p>book detail</p>
    <p><%=request.getParameter("book")%></p>
    <a href="index.jsp">返回</a>
    <%
        // 获取购买的书信息
        String book = request.getParameter("book");
        // 获取所有的cookie，看看总数是否大于5，主页最多只显示5本书
        Cookie[] cookies = request.getCookies(); //获取所有cookie
        // 获取所有书信息
        List<Cookie> bookCookies = new ArrayList<>();
        // 用来保存和主页传入的book匹配的cookie
        Cookie tempCookie = null;
        if (cookies !=null && cookies.length > 0){
            for (Cookie value : cookies){
                if (value.getName().startsWith("BOOK_")){
                    bookCookies.add(value);

                    if (book.equals(value.getValue())){
                        tempCookie = value;
                    }
                }
            }
        }
        // 若从主页传入的book不在BOOK_的cookies中则删除较早的那个cookie
        if (bookCookies.size()>=5 && tempCookie == null){
            tempCookie = bookCookies.get(0);
        }
        //删除
        if (tempCookie != null){
            tempCookie.setMaxAge(0); //删除
            response.addCookie(tempCookie);
        }
        //存入cookie
        Cookie cookie = new Cookie("BOOK_"+book, book); //当以BOOK_开头表示书的信息
        response.addCookie(cookie);
    %>
</body>
</html>
