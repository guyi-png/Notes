<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>俺的网站</title>
    <style>
        div{
            background-color: rgba(131, 201, 124, 0.1);
            position: relative;
            width: 1000px;
            margin: 0 auto;
            font-size: 20px;
            text-align: center;
        }
        div a:first-of-type {
            font-size: 250px;
            text-decoration: none;
            color: red;
        }
        div a:nth-of-type(2){
            position: absolute;
            top: 10px;
            right: 10px;
            text-decoration: none;
        }
        div a:hover{
            color:blue;
        }
    </style>
</head>
<body>
    <div>
        <h1>购买成功！</h1>
        <p>感谢您的支持！！！</p>
        <a href="booksServlet?method=getBooks">俺还要买</a>
        <a href="booksServlet?method=toPage&pageName=trade">查看交易记录</a>
    </div>
</body>
</html>