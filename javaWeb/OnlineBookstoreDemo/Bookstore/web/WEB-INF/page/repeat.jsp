<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>表单重复</title>
    <style>
        p{
            text-align: center;
        }
        a{
            font-size: 30px;
        }
        a:hover{
            color: red;
        }
    </style>
</head>
<body>
    <p>表单提交重复</p>
    <p><a href="booksServlet?method=toPage&pageName=success">您购买了所选书, 到成功页面</a></p>
</body>
</html>