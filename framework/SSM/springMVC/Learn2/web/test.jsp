<%--
  User: 古乂
  Date: 2020/8/24
  Time: 13:24
  Content: 类型转化问题
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
    <!--将指定格式(name-age-department.id)的字符串转为Employee对象-->
    <form action="testConversionServiceConverter" method="post">
        Employee: <input type="text" name="employee">
        <button>提交</button>
    </form>
    <p>格式 name-age-department.id</p>
</body>
</html>
