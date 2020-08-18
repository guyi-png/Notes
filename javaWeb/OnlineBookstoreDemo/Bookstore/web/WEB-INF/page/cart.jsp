<%@ page import="com.bookstore.mvc.model.domain.ShoppingCart" %>
<%@ page import="com.bookstore.mvc.model.domain.ShoppingCartItem" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.bookstore.mvc.model.domain.Book" %>
<%--
  User: 古乂
  Date: 2020/8/16
  Time: 12:43
  Content: 购物车的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>俺的购物车</title>
    <style>
        div{
            width: 1000px;
            margin: 0 auto;
            text-align: center;
            background-color: rgba(10,121,128,0.06);
        }
        div table {
            width: 100%;
            border-collapse: collapse;
        }
        div table td , div table th{
            text-align: center;
            padding: 15px;
            border: 1px solid gray;
        }
        input{
            width: 100px;
            font-size: 18px;
            outline: none;
            border: none;
        }
        a{
            text-decoration: none;
            color: blue;
        }
        a:hover{
            color: red;
        }
        div p {
            margin: 10px;
            font-size: 25px;
        }
        footer{
            font-size: 30px;
            text-align: center;
        }
        footer a:not(:nth-of-type(1)) {
            padding-left: 100px;
        }
    </style>
    <script src="script/jQuery-v3.4.1.js"></script>
    <script>
        window.onload = function(){
            (function() {
                let deleteBookNode = document.querySelectorAll(".deleteBook");
                for (let i = 0; i < deleteBookNode.length; i++) {
                    deleteBookNode[i].onclick = function () {
                        let titleName = document.querySelectorAll(".title")[i].innerText;
                        return confirm("您真要删除《" + titleName + "》这本书吗？");
                    }
                }
            })();
        }


        // 使用ajax请求更改书的数量
        $(()=>{
            // 获取所有的input标签,并添加事件
            $("input[type=number]").change(function(){
                let flag = confirm("您真就要修改此书的数量？");
                let quantity = $(this).val().trim();
                if (flag){
                    // 请求的地址
                    let url = "booksServlet";
                    //请求的参数
                    let idStr = $(this).attr("name").trim();
                    let args = {
                        "method": "updateItemQuantity",
                        "id": idStr,
                        "quantity": quantity,
                        "time": new Date()
                    }
                    // 发送请求, 通过回调函数获取返回的数据
                    $.post(url, args, (data)=>{
                        let bookNumber = data.bookNumber;
                        let totalMoney = data.totalMoney;
                        // 更新数据
                        $(".bookNumber").text("书的总数: "+bookNumber);
                        $(".totalMoney").text("总价: ￥"+totalMoney+"元");

                        // location.reload();
                        //浏览器重新从服务器请求资源,在http请求头中不会包含缓存标记
                        location.reload(true);
                        /*
                        * 注： value的值是用来表示组件的初始值的，
                        * 如果后期修改了input的value是不会更新在页面上的
                        * */

                    }, "JSON");
                }else{
                    location.reload(true);
                    return flag;
                }
            });
        })
    </script>
</head>
<body>
    <%
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");

        String paramStr = "";
        if (request.getQueryString().contains("Price")){
            paramStr = "&minPrice="+request.getParameter("minPrice") +
                    "&maxPrice="+request.getParameter("maxPrice");
        }
    %>
    <div>
        <h1>购物车</h1>
        <table>
            <tr>
                <th>书名</th>
                <th>价格</th>
                <th>数量</th>
                <th>删除</th>
            </tr>
           <%
               Collection<ShoppingCartItem> items = shoppingCart.getItems();
               for (ShoppingCartItem item : items){
                   Book book = item.getBook();
           %>
           <tr>
               <td><span class="title"><%=book.getTitle()%></span></td>
               <td><span><%=book.getPrice()%></span></td>
               <td><input type="number" name="<%=book.getBook_id()%>" value="<%=item.getQuantity()%>"></td>
               <td><a class="deleteBook" href="booksServlet?method=remove&pageNo=${param.pageNo}&id=<%=
               book.getBook_id()%><%=paramStr%>">删除此书</a></td>
           </tr>
           <%}%>
        </table>
        <p class="bookNumber">
            书的总数: <%=shoppingCart.getBookNumber()%>
        </p>
        <p class="totalMoney">
            总价: ￥<%=shoppingCart.getTotalMoney()%>元
        </p>
    </div>
    <footer>
        <a href="booksServlet?method=getBooks&pageNo=${param.pageNo}<%=paramStr%>">继续购物</a>
        <a href="booksServlet?method=clear&pageNo=${param.pageNo}<%=paramStr%>">清空购物车</a>
        <a class="buyBook" href="booksServlet?method=toPage&pageName=buy&pageNo=${param.pageNo}<%=paramStr%>">购买</a>
    </footer>
</body>
</html>
