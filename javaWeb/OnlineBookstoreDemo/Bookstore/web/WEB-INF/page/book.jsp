<%@ page import="com.bookstore.mvc.model.domain.Book" %><%--
  User: 古乂
  Date: 2020/8/15
  Time: 18:25
  Content: 单本书的简介页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>《${requestScope.book.title}》的简介</title>
    <style>
        *{
            text-align: center;
        }
        div{

            width: 1000px;
            margin: 200px auto 0;
        }
        table{
            width: 100%;
            border-collapse: collapse;
        }
        table th, table td {
            padding: 20px;
            border: 1px solid black;
        }
        div a {
            font-size: 50px;
            text-decoration: none;
        }
        div a:hover {
            color: red;
        }
    </style>
</head>
<body>
    <%
        Book book = (Book)request.getAttribute("book");

        String paramStr = "";
        if (request.getQueryString().contains("Price")){
            paramStr = "&minPrice="+request.getParameter("minPrice") +
                        "&maxPrice="+request.getParameter("maxPrice");
        }
    %>
    <div>
        <table>
            <tbody>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>价格</th>
                    <th>出版日期</th>
                    <th>销售额</th>
                    <th>评论</th>
                </tr>
                <tr>
                    <%
                        if (book != null){
                    %>
                    <td><%=book.getTitle()%></td>
                    <td><%=book.getAuthor()%></td>
                    <td><%=book.getPrice()%></td>
                    <td><%=book.getPublishing_date()%></td>
                    <td><%=book.getSales_amount()%></td>
                    <td><%=book.getRemark()%></td>
                    <%
                        } else{
                    %>
                    <td colspan="6">没有书的信息</td>
                    <%}%>
                </tr>
            </tbody>
        </table>
        <a href="booksServlet?method=getBooks&pageNo=${param.pageNo}<%=paramStr%>">继续购物</a>
    </div>
</body>
</html>
