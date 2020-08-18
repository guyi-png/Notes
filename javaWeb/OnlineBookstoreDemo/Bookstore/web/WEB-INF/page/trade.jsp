<%@ page import="com.bookstore.mvc.model.domain.User" %>
<%@ page import="com.bookstore.mvc.model.domain.Trade" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.bookstore.mvc.model.domain.TradeItem" %><%--
  User: 古乂
  Date: 2020/8/18
  Time: 13:09
  Content: 用户交易记录
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户交易记录</title>
    <style>
        a{
            margin: 30px;
            text-decoration: none;
        }
        a:hover{
            color: red;
        }
        form{
            font-size: 16px;
            text-align: center;
        }
        div{
            font-size: 25px;
            text-align: center;
        }
        .trade{
            border: 1px dotted black;
        }
    </style>
</head>
<body>
    <a href="booksServlet?method=getBooks">回到书城首页</a>
    <form action="userServlet" method="post">
        请输入用户名查看记录-->
        <label>
            <input type="text" name="username" placeholder="请输入用户名查看记录"
            value="${param.username}">
        </label>
        <button>查看</button>
        <---------
    </form>

    <p>${requestScope.message}</p>
        <%
            User user = (User)request.getAttribute("user");
            if (user != null){
                Set<Trade> trades = user.getTrades();
                for (Trade trade : trades){
        %>
    <div class="trade">
            <p>交易时间: <%=trade.getTrade_time()%></p>
            <%
                for (TradeItem tradeItem : trade.getItems()){
            %>
            <p>书名： <%=tradeItem.getBook().getTitle()%></p>
            <p>价格： <%=tradeItem.getBook().getPrice()%></p>
            <p>购买数量： <%=tradeItem.getQuantity()%></p>
            <p>********************************************************</p>
            <br>
        <%
                    }
        %>
    </div>
        <%
                }
            }
        %>

    <script>
        let buttonNode = document.getElementsByTagName("button")[0];
        buttonNode.onclick = function(){
            let inputNode = document.getElementsByTagName("input")[0];
            let isValid = true;
            if (inputNode.value.trim() === ""){
                isValid = false;
            }
            return isValid;
        }
    </script>
</body>
</html>
