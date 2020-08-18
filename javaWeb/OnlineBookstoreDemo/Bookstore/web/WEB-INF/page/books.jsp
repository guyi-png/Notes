<%@ page import="com.bookstore.mvc.model.web.Page" %>
<%@ page import="com.bookstore.mvc.model.domain.Book" %>
<%@ page import="com.bookstore.mvc.model.domain.ShoppingCart" %><%--
  User: 古乂
  Date: 2020/8/14
  Time: 18:11
  Content: 书城首页
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>书城首页</title>
    <style>
        main{
            width: 1000px;
            background-color: rgba(10,121,128,0.06);
            display: flex;
            flex-direction: column;
            margin: 0 auto;
        }
        form, h1{
            margin: 0 auto 20px;
        }
        label, input, button{
            font-size: 20px;
        }
        main p {
            text-align: center;
            margin: 0 auto 20px;
        }
        main p a{
            margin-left: 10px;
            text-decoration: none;
            color: #ffa200;
        }
        main p a:hover{
            color: red;
        }
        table{
            border-collapse: collapse;
        }
        table th, td{
            width: 200px;
            border: 1px solid black;
            text-align: center;
            padding: 15px;
        }
        table a{
            display: block;
            width: 100%;
            text-decoration: none;
            color: blue;
        }
        table a:hover{
            color: red;
        }
        .page-info{
            display: flex;
            align-items: baseline;
            justify-content: center;
            margin: 15px;
            font-size: 20px;
        }
        .page-info li{
            list-style: none;
        }
        .page-info span, .page-info button{
            margin-left: 20px;
        }
        .page-info button{
            padding: 0;
        }
        .page-info a{
            display: block;
            width: 100%;
            text-decoration: none;
            color: black;
        }
        .page-info li input{
            padding: 0 5px;
            outline:none;
        }
    </style>
    <script src="script/jQuery-v3.4.1.js"></script>
    <script>
        $(()=>{
            // 通过hidden标签获取要用的queryBody的字符串
            function getSerializer(){
                let $hidden = $("input[type=hidden]");
                let serializer = "";
                if ($hidden.val() !== ""){
                    serializer = "&"+$hidden.serialize();
                }
                return serializer;
            }

            // 保存页面间筛选条件
           $("a").click(function(){
               // 通过全局属性(window.location)更改地址栏
               if (!this.href.includes("#")) {
                   location.href = this.href + getSerializer();
               }
               // 一定要取消默认行为，否则还是向原a的href跳转
               return false;
           });

            // 当该标签改变时的事件
            $(".page-info li input").change(function(){
                let value = $(this).val().trim();
                if (value === "")return;
                // 校验value是否为整数
                    //使用正则表达式
                let regExp = /^\d+/g;
                if (!regExp.test(value)){
                    alert("输入的页数有误");
                    $(this).val("");
                    return;
                }
                // 获取提交的页数
                let pageNo = parseInt(value);
                // 校验value是否为有效
                if (value < 1 || value > ${requestScope.page.pageSize}){
                    alert("页数无效");
                    $(this).val("");
                    return;
                }
                // 现在可以跳转页面了
                location.href = "booksServlet?method=getBooks&pageNo="+ pageNo + getSerializer();
            });

            // 验证输入的筛选条件是否有误
            $("form button ").click(()=>{
                let val = $("form input").val().trim();
                if (val === ""){
                    return true;
                }
                if (!parseFloat(val)){
                    alert("筛选条件有误");
                    return false;
                }
            });

        });
    </script>
</head>
<body>
    <main>
        <h1>网上书城</h1>
        <input type="hidden" name="minPrice" value="${param.minPrice}">
        <input type="hidden" name="maxPrice" value="${param.maxPrice}">
        <form action="booksServlet?method=getBooks" method="post">
            <label>
                通过价格筛选书(无条件将显示所有结果):&nbsp;
                <input type="text" name="minPrice" placeholder="输入最小价格" size="10" value="${param.minPrice}">
                ~
                <input type="text" name="maxPrice" placeholder="输入最大价格" size="10" value="${param.maxPrice}">
            </label>
            <button>筛选</button>
        </form>
        <%
            Page<Book> bookPage = (Page<Book>) request.getAttribute("page");
            ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
            String title = (String) request.getAttribute("title");

            if (title != null){

        %>
        <p>您已经将《<%=title%>》放入了购物车</p>
        <%
            }
            if (shoppingCart != null && !shoppingCart.isEmpty()){
        %>
        <p>您的购物车中有<%=shoppingCart.getBookNumber()%>本书,
            <a href="booksServlet?method=toPage&pageName=cart&pageNo=<%=bookPage.getPageNo()%>">点击查看购物车</a></p>
        <%
            }
        %>
        <table>
            <tbody>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>价格</th>
                    <th>购买</th>
                </tr>
            <%
                for (Book book : bookPage.getList()){
            %>
                <tr>
                    <td>
                        <a href="booksServlet?method=getBook&pageNo=<%=
                        bookPage.getPageNo()%>&id=<%=book.getBook_id()%>"><%=book.getTitle()%>
                        </a>
                    </td>
                    <td><%=book.getAuthor()%></td>
                    <td><%=book.getPrice()%></td>
                    <td><a class="addToCart" href="booksServlet?method=addToCart&pageNo=<%=
                        bookPage.getPageNo()%>&id=<%=book.getBook_id()%>">加入购物车</a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <ul class="page-info">
            <li><span>共<%=bookPage.getPageSize()%>页</span></li>
            <li><span>当前第<%=bookPage.getPageNo()%>页</span></li>
            <%  if (bookPage.hasPrev()){    %>
            <li><button><a href="booksServlet?method=getBooks&pageNo=1">首页</a></button></li>
            <li><button><a href="booksServlet?method=getBooks&pageNo=<%=bookPage.getPageNo()-1%>">上一页</a></button></li>
            <%  }else{   %>
            <li><button><a href="#">首页</a></button></li>
            <li><button><a href="#">上一页</a></button></li>
            <%}%>
            <%  if (bookPage.hasNext()){    %>
            <li><button><a href="booksServlet?method=getBooks&pageNo=<%=bookPage.getPageNo()+1%>">下一页</a></button></li>
            <li><button><a href="booksServlet?method=getBooks&pageNo=<%=bookPage.getPageSize()%>">尾页</a></button></li>
            <%  }else{   %>
            <li><button><a href="#">下一页</a></button></li>
            <li><button><a href="#">尾页</a></button></li>
            <%}%>
            <li><label><span>跳转到第</span> <input type="text" size="1"> 页</label></li>
        </ul>
    </main>
</body>
</html>
