<%@ page import="com.mvc.model.customer.Customer" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/25
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>添加客户</title>
    <style>
        div, h1{
            text-align: center;
        }
        p{
            color: red;
            text-align: center;
        }
        h3{

            font-size: 30px;
            color: chocolate;
        }
        input[type=submit]{
            color:green;
            font-size: 30px;
        }
        span{
            color: red;
        }
    </style>
</head>
<body>
    <%String message = (String) request.getAttribute("message");%>
    <h1>新客户</h1>
    <p>请完善信息( '*' 表示必需要填的信息)，添加数据</p>
    <div>
        <form action="add.customer" method="post">
            <label>
                CustomerName<span>*</span>  :
                <input required type="text" name="name" value="<%=request.getParameter("name")==null? "":request.getParameter("name")%>"/>
            </label>
            <label>
                Address<span>*</span>  :
                <input required type="text" name="address" value="<%=request.getParameter("address")==null? "":request.getParameter("address")%>"/>
            </label>
            <label>
                Phone<span>*</span>  :
                <input required type="text" name="phone" value="<%=request.getParameter("phone")==null? "":request.getParameter("phone")%>"/>
            </label>
            <br>
            <br>
            <input type="submit" value="确定添加">
        </form>
        <h3><%=message ==null? "": message%></h3>
    </div>
</body>
</html>
