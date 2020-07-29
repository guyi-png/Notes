<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <style>
        h4,table{
            margin: 0 auto;
            text-align: center;
        }
        table {
            margin-top: 20px;
            border-collapse:collapse;
        }
        th,td{
            padding: 10px;
            border: 2px solid black;
        }
        tr:last-child td:last-child{
            padding: 0;
            border: none;
        }
        td input[type=submit]{
            width: 100%;
            font-size: 30px;
        }
    </style>
</head>
<body>
    <h4>step2, 请输入寄送地址和信用卡信息</h4>
    <form action="<%=request.getContextPath()%>/processStep2" method="post">
        <table>
            <tbody>
                <tr><td colspan="2">寄送信息</td></tr>
                <tr>
                    <td>姓名 </td>
                    <td><input type="text" name="name" required></td>
                </tr>
                <tr>
                    <td>寄送地址 </td>
                    <td><input type="text" name="address" required></td>
                </tr>
                <tr><td colspan="2">信用卡信息 </td></tr>
                <tr>
                    <td>种类 </td>
                    <%-- radio的name要一样，表示单选--%>
                    <td><input type="radio" name="cardType" value="visa" required>Visa
                        <input type="radio" name="cardType" value="Master" required>Master</td>
                </tr>
                <tr>
                    <td>卡号 </td>
                    <td><input type="text" name="card" required></td>
                </tr>
                <tr><td colspan="2"><input type="submit" value="确认提交信息"></td></tr>
            </tbody>
        </table>
    </form>
</body>
</html>
