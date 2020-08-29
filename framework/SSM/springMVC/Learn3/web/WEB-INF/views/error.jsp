<%--
  User: 古乂
  Date: 2020/8/25
  Time: 16:54
  Content: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的网站</title>
    <style>
        *{
            text-align: center;
        }
        #r{
            margin: 0;
            padding: 0;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            font-size: 120px;
            color: red;
        }
        #r:hover{
            color: blue;
        }
    </style>
</head>
<body>
    <p id="r">就这？</p>
    ${requestScope.exception}
    ${requestScope.e}
</body>
</html>
