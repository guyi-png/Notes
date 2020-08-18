<%--
  User: 古乂
  Date: 2020/8/16
  Time: 15:42
  Content: 空购物车
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的购物车</title>
    <style>
        h1, a{
            display: block;
            text-align: center;
            text-decoration: none;
        }
        a{
            font-size: 30px;
            color: blue;
        }
        a:hover{
            color: red;
        }
    </style>
</head>
<body>
    <%
        String paramStr = "";
        if (request.getQueryString().contains("Price")){
            paramStr = "&minPrice="+request.getParameter("minPrice") +
                    "&maxPrice="+request.getParameter("maxPrice");
        }
    %>
    <h1>购物车为空</h1>
    <a href="booksServlet?method=getBooks&pageNo=${param.pageNo}<%=paramStr%>">继续购物</a>
</body>
</html>
