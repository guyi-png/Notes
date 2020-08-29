<%--
  User: 古乂
  Date: 2020/8/22
  Time: 10:30
  Content: 向Java的类发送请求
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
</head>
<body>
    <a href="handler/hello">点我！</a>

    <form action="handler/testMethod" method="post">
        <button>post请求</button>
    </form>

    <a href="handler/testParameter?username=mike&age=11">testParameter</a>

    <a href="handler/testAntPath/阿凡达/ok">阿凡达</a>

    <a href="handler/testPathVariable/ok">testPathVariable</a>


    <a href="handler/testRest/1">GET请求</a>
    <form action="handler/testRest" method="post">
        <button>POST请求</button>
    </form>
    <form action="handler/testRest/1" method="post">
        <!--隐藏域会被hiddenHttpMethodFilter获取，这的name得是"_method"，value是要转换的方法 -->
        <input type="hidden" name="_method" value="DELETE">
        <button>DELETE请求</button>
    </form>
    <form action="handler/testRest/1" method="post">
        <input type="hidden" name="_method" value="PUT">
        <button>PUT请求</button>
    </form>


    <a href="handler/testRequestParam?username=olg&password=0942">带参的请求</a>

    <a href="handler/testRequestHeader">testRequestHeader</a>

    <a href="handler/testCookieValue">testCookieValue</a>

    <!--表单为类属性赋值-->
    <form action="handler/testPojo" method="post">
        username: <input type="text" name="username">
        password: <input type="password" name="password">
        city: <input type="text" name="address.city">  <!--为级联属性赋值-->
        street: <input type="text" name="address.street">
        <button>提交</button>
    </form>

    <a href="handler/testServletAPI">testServletAPI</a>

    <a href="handler/testModelAndView">testModelAndView</a>

    <a href="handler/testMap">testMap</a>

    <a href="handlerTwo/testSessionAttribute">testSessionAttribute</a>

    <form action="handlerTwo/testModeAttribute" method="post">
        <input type="hidden" name="id" value="1">
        username: <input type="text" name="username">
        <button>提交</button>
    </form>

    <a href="handlerTwo/testViewAndViewResolver">testViewAndViewResolver</a>

    <a href="hello">to hello</a>

    <a href="handlerTwo/testView">testView</a>

    <a href="handlerTwo/testRedirect">testRedirect</a>
</body>
</html>
