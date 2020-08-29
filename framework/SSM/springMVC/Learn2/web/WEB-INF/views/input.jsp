<%@ page import="com.springMVC.entity.Employee" %><%--
  User: 古乂
  Date: 2020/8/23
  Time: 17:00
  Content: 添加员工的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--使用spring的form标签，可以更快开发，更方便进行表单的回显-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
<!--可以通过modelAttribute属性指定模型属性，
若没有指定该属性，则默认从request域中读取command的表单bean, 若读取不到command的表单bean则报错-->
    <form:form action="${pageContext.request.contextPath}/emps/emp" method="post" modelAttribute="employee">
        <!--当更新时不修改name属性-->
        <%Employee employee = (Employee) request.getAttribute("employee");
                if (employee.getId() == null){%>

        name: <form:input path="name"/>  <!--该path属性与html中表单的name属性对应-->
              <form:errors path="name" cssStyle="color:red"/>

                <%}else{%>

        <form:hidden path="id"/>   <!--保存id的信息-->
        <!--对于_method不能使用form:hidden标签， 因为modelAttribute中没有_method属性-->
        <input type="hidden" name="_method" value="PUT">
                <%}%>



        age：<form:input path="age"/>

        <!--使用级联属性为employee中的department属性的id属性赋值-->
        department: <form:select path="department.id"
                     items="${departments}" itemLabel="departmentName" itemValue="id"/>
                        <!--获取request域中的数据-->

        birth: <form:input path="birth"/>
        <form:errors path="birth" cssStyle="color:red"/>

        salary: <form:input path="salary"/>
        <form:button>提交</form:button>
<%--        <!--表单数据出错时显示-->--%>
<%--        <form:errors path="*"/>
            可以直接加到对应属性后面--%>
    </form:form>
</body>
</html>
