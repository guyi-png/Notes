<%@ page import="java.util.Collection" %>
<%@ page import="com.springMVC.entity.Employee" %>

<%--
  User: 古乂
  Date: 2020/8/23
  Time: 16:02
  Content: 显式员工信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
    <script src=""></script>
</head>
<body>
<%
    Collection<Employee> employees =
            (Collection<Employee>) request.getAttribute("employees");
%>
    <table>
        <tr>
            <th>名字</th>
            <th>年龄</th>
            <th>公司</th>
            <th>编辑</th>
            <th>删除</th>
        </tr>
        <% if (employees != null && employees.size() > 0){
                for (Employee employee : employees){
        %>
            <tr>
                <td><%=employee.getName()%></td>
                <td><%=employee.getAge()%></td>
                <td><%=employee.getDepartment().getDepartmentName()%></td>
                <td><a href="${pageContext.request.contextPath}/emps/emp/<%=employee.getId()%>" class="editA">edit</a></td>
                <td><a href="${pageContext.request.contextPath}/emps/emp/<%=employee.getId()%>" class="deleteA">delete</a></td>
            </tr>
        <%
                }
        }%>
    </table>
<!--弄两个隐藏域,用于将post转为delete或put-->
    <form action="" method="post" id="delete">
        <input type="hidden" name="_method" value="DELETE">
    </form>

<a href="emp">添加员工</a>
<!--这js用专门的编辑器写吧-->
<script type="application/javascript">
    // 让a标签的请求禁用转用form表单，实现GET->POST, 然后通过隐域将POST转为对应的DELETE或PUT请求
    window.onload = function(){
        let aList = document.querySelectorAll(".deleteA");
        let deleteForm = document.getElementById("delete");
        for (let a of aList){
            a.onclick = function(){
                deleteForm.setAttribute("action", this.href);
                deleteForm.submit();
                return false;
            }
        }
    }
</script>
<!--
SpringMVC处理静态资源： 若将DispatcherServlet请求映射配置为/ ，则
SpringMVC会将捕获到的所有请求，都会将他们当成一个普通的请求处理，因而找不到对应的处理器而报错
解决办法： 在SpringMVC的配置文件中配置&lt;mvc:default-servlet-handler&gt;的方式解决静态资源的问题
-->
<script src="${pageContext.request.contextPath}/script/jquery.js"></script>
</body>
</html>
