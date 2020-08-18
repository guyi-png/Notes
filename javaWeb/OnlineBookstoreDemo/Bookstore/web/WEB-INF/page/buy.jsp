<%@ page import="java.util.Date" %><%--
  User: 古乂
  Date: 2020/8/16
  Time: 20:02
  Content: 结账页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结账</title>
    <style>
        div{
            width: 1000px;
            margin: 200px auto 0;
            text-align: center;
            background: rgba(210, 105, 30, 0.2);
            position: relative;
        }
        input[type=submit]{
            font-size: 25px;
        }
        a{
            position: absolute;
            bottom: 0;
            right: 0;
            text-decoration: none;
            font-size: 25px;
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
    <div>
        <h1>您正在购买所选的书</h1>
        <p>您一共购买了${sessionScope.shoppingCart.bookNumber}本书</p>
        <p>需要支付￥${sessionScope.shoppingCart.totalMoney}元</p>
        <p>请输入您的用户名和账户id完成购买</p>
        <%
            // 表单的标记
            session.setAttribute("token", new Date().getTime()+""+hashCode());
        %>
        <form action="booksServlet?method=cash&pageNo=${param.pageNo}<%=paramStr%>" method="post">
            <%--   隐藏标签用于验证token--%>
            <input type="hidden" name="token" value="<%=session.getAttribute("token")%>">
            <label>用户名：<input type="text" name="username" value="${param.username}"></label>
            <label>账户id：<input type="text" name="accountId" value="${param.accountId}"></label>
            <br>
            <br>
            <input type="submit" value="购买">
            <br>
            <span style="color: red">${requestScope.message}</span>
        </form>
        <a href="booksServlet?method=toPage&pageName=cart&pageNo=${param.pageNo}<%=paramStr%>">回到购物车</a>
    </div>
</body>
</html>
