<%@ page import="com.mvc.model.customer.Customer" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/25
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>修改客户信息</title>
</head>
<style>
    div, h1{
        text-align: center;
    }
    h3{

        font-size: 30px;
        color: chocolate;
    }
    p{
        color: red;
        text-align: center;
    }
    input[type=submit]{
        color:green;
        font-size: 30px;
    }
</style>
<body>
    <%
        Customer customer = (Customer)request.getAttribute("customer");
        String message = (String)request.getAttribute("message");
        if (customer == null){
            customer = new Customer();
        }
    %>
    <h1>修改信息</h1>
    <p>提示：Customer必须修改，修改完点击确定修改</p>
    <div>
        <form action="update.customer" method="post">
            <input type="hidden" name="id" value="<%=customer.getId()%>">    <!-- 添加隐藏标签 -->
            <input type="hidden" name="originalName" value="<%=customer.getName()%>">
            <label>
                CustomerName<span>*</span>  :
                <input required type="text" name="name" value="<%=customer.getName()%>"/>
            </label>
            <label>
                Address<span>*</span>  :
                <input required type="text" name="address" value="<%=customer.getAddress()%>"/>
            </label>
            <label>
                Phone<span>*</span>  :
                <input required type="text" name="phone" value="<%=customer.getPhone()%>"/>
            </label>
            <br>
            <br>
            <input type="submit" value="确定修改">
        </form>
        <h3><%=message ==null? "": message%></h3>
    </div>
</body>
</html>
