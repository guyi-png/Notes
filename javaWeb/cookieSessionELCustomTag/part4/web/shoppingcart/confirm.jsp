<%@ page import="com.guyi.servlet.com.guyi.Customer" %>
<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 15:24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>确认信息</title>
</head>
<body>
<%
    com.guyi.Customer customer = (com.guyi.Customer) session.getAttribute("customer");
    String[] books = (String[]) session.getAttribute("books");
%>
    <table>
        <tbody>
            <tr>
                <td>客户姓名: &nbsp;</td>
                <td><%=customer.getName()%></td>
            </tr>
            <tr>
                <td>寄送地址: &nbsp;</td>
                <td><%=customer.getAddress()%></td>
            </tr>
            <tr>
                <td>卡号: &nbsp;</td>
                <td><%=customer.getCard()%>(<%=customer.getCardType()%>)</td>
            </tr>
            <tr>
                <td>购买的书: &nbsp;</td>
                <td>
                    <%
                        for (String book : books){
                            out.println(book+" ");
                        }
                    %>
                </td>
            </tr>
        </tbody>
    </table>
</body>
</html>
