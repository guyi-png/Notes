<%@ page import="java.util.List" %>
<%@ page import="com.guyi.mvc.Student" %>
<%--
  User: 古乂
  Date: 2020/7/23
  Time: 17:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示所有学生</title>
    <style>
        *{
            font-size: 20px;
        }
        table{
            position: absolute;
            top: 30%;
            left: 35%;
            border-collapse: collapse;
            border: 1px solid black;
            text-align: center;
        }
        table tr:nth-of-type(odd){
            background-color: rgba(187, 184, 184, 0.1);
        }
        table tr:nth-of-type(even){
            background-color: gray;
        }
        table th, table td{
            padding: 15px;
            border: 1px solid black;
        }
        a{
            color: #28d920;
            text-decoration: none;
        }
        a:hover{
            color: red;
        }
    </style>
</head>
<body>
    <%
        List<Student> students =  (List<Student>)request.getAttribute("students");
    %>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>gender</th>
            <th>age</th>
            <th>删除</th>
        </tr>
        <%
            for (Student student : students){
        %>
            <tr>
                <td><%=student.getId()%></td>
                <td><%=student.getName()%></td>
                <td><%=student.getGender()%></td>
                <td><%=student.getAge()%></td>
                <td><a href="delete?id=<%=student.getId()%>">删除<%=student.getName()%></a></td>
            </tr>
        <%
            }
        %>
    </table>
</body>
</html>
