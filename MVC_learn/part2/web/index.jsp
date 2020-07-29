<%@ page import="com.mvc.model.customer.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/24
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
  <head>
    <title>查询客户</title>
    <style>
        div, h1{
            text-align: center;
        }
        p{
            color: red;
            text-align: center;
        }
        table{
            margin: 0  auto;
            text-align: center;
            border-collapse: collapse;
        }
        table tr:nth-of-type(odd){
            background-color: rgba(128, 128, 128, 0.5);
        }
        th, td{
            padding: 10px;
            border: 1px solid black;
        }
        a{
            text-decoration: none;
            color: green;
        }
        a:hover{
            color: red;
        }
    </style>
  </head>
  <body>
<%--        JSP ->  Servlet -> DAO -> database(mysql)
            jsp负责view， servlet负责controller， dao负责model    --%>
    <h1>查询</h1>
    <p>按以下条件查询，若无任何条件将显示全部信息</p>
    <div>
        <form action="select.customer" method="post">
            <label>
                CustomerName :
                <input type="text" name="name"/>
            </label>
            <label>
                Address :
                <input type="text" name="address"/>
            </label>
            <label>
                Phone :
                <input type="text" name="phone"/>
            </label>
            <br>
            <br>
            <input type="submit" value="点击查询">
            <button><a href="addNewCustomer.jsp">添加新客户</a></button>
        </form>

        <%
            List<Customer> customers = (List<Customer>)request.getAttribute("customers");
            if (customers != null && customers.size() > 0){
        %>
            <table>
                <tr>
                    <th>ID</th>
                    <th>CustomerName</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>更新</th>
                    <th>删除</th>
                </tr>
                <%
                    for (Customer customer : customers){
                %>
                    <tr>
                        <td><%=customer.getId()%></td>
                        <td><%=customer.getName()%></td>
                        <td><%=customer.getAddress()%></td>
                        <td><%=customer.getPhone()%></td>
                        <td><a class="update" href="edit.customer?id=<%=customer.getId()%>">更新</a></td>
                        <td><a class="delete" href="delete.customer?id=<%=customer.getId()%>">删除</a></td>
                    </tr>
                <%
                    }
                %>
            </table>
        <%
            }
        %>
    <script src="./script/jQuery%20v3.4.1.js"></script>
    <script>
        // 当删除信息时，询问是否要删除
        $(()=>{
           $(".delete").click(function(){       // 隐式遍历绑定click事件
               let name = $(this).parent().parent().find("td:eq(1)").text(); // 找到对应的名字
               return confirm("你确定要 "+name+" 删除吗");
           });
        });
    </script>
    </div>
  </body>
</html>
