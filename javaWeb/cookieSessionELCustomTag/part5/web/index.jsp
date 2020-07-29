<%@ page import="com.guyi.Customer" %><%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 20:46
  Content: el(expression language)的学习
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL表达式</title>
</head>
<body>
    <%request.setCharacterEncoding("UTF-8");%>
    <form action="index.jsp" method="post">
        <label>username:<input type="text" name="username"
                 value="${param.username}"/></label>  <!--使用el-->
        <input type="submit" value="提交">
    </form>
    <a href="index.jsp?age=19">在?</a>
    <%
        Customer customer = new Customer();  // Customer是javaBean
        session.setAttribute("customer", customer);
    %>

    <%-- 以下两种等价   --%>
    <%
        // 普通的获取属性
        Customer customer1 = (Customer) session.getAttribute("customer");
        out.println(customer1.getName());
    %>
<%--   使用el , 只要它的属性中getter(getXx()无参方法)就可以.xx
 sessionScope.customer相当于session.getAttribute("customer")
 紧接着customer.name或者customer["name"]相当于customer1.getName()--%>
    ${sessionScope.customer.name}
    ${sessionScope.customer["name"]}
<%--
    el变量：当使用${xx},它表示取出某范围中名称为xx的变量，没有指定域，它会从page范围开始找
    然后依次到request，session， application找，找到就返回，没有就返回null
--%>
<%--    获取request的参数, el可以进行自动的类型转换--%>
    ${param.age+10}

    <a href="<%=request.getContextPath()%>/el.jsp?name=olg&age=19&age=20">到el.jsp</a>
</body>
</html>
