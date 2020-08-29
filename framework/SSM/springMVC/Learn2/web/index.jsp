
<%--
  User: 古乂
  Date: 2020/8/21
  Time: 21:40
  Content: 模拟curd操作
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>俺的网站</title>
    <script src="script/jquery.js"></script>
    <script type="application/javascript">
      //////  注意箭头函数的this指向
      $(()=>{
        $("#testJson").click(function(){
          let url = this.href;
          let args = {};
          $.post(url, args, (data)=>{
            for (let employee of data){
              let id = employee.id;
              let name = employee.name;
              let age = employee.age;
              console.log(id+", "+ name+ ","+age);
            }
          });
          return false;
        });
      });
    </script>
  </head>
  <body>
    <a href="${pageContext.request.contextPath}/emps/list">展示员工列表</a>
    <br>
    <a id="testJson" href="${pageContext.request.contextPath}/emps/testJson">testJson</a>
    <br>
    <form action="${pageContext.request.contextPath}/emps/testMessageConverter" method="post" enctype="multipart/form-data">
      文件： <input type="file" name="file">
      描述： <input type="text" name="desc">
      <button>提交</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/emps/testResponseEntity">testResponseEntity</a>
    <br>
    <form action="${pageContext.request.contextPath}/emps/testFileupload" method="post" enctype="multipart/form-data">
      文件： <input type="file" name="file">
      描述： <input type="text" name="desc">
      <button>提交</button>
    </form>
    <br>
  </body>
</html>
