<%--
  Created by IntelliJ IDEA.
  User: 古乂
  Date: 2020/7/27
  Time: 13:50
  Content: 利用session，完成案例
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Step1</title>
  </head>
  <style>
    h4,table{
      margin: 0 auto;
      text-align: center;
    }
    table {
      margin-top: 20px;
      border-collapse:collapse;
    }
    th,td{
      padding: 10px;
      border: 2px solid black;
    }
    tr:last-child td:last-child{
      padding: 0;
      border: none;
    }
    td input[type=submit]{
      width: 100%;
      font-size: 30px;
    }
  </style>
  <body>
    <h4>步骤1，选择要购买的图书</h4>
    <%--
        http://localhost:8080/contextPath/xx 是绝对路径
        http://localhost:8080/xx 不是绝对路径
        javaWEB开发中“ / ”代表的路径：
            1.表示WEB根路径 http://localhost:8080/contextPath
                请求转发时使用/xx
                web.xml的url-pattern中/xx
            2.表示WEB站点根路径 http://localhost:8080
                html中的a标签，form标签中的action
                重定向是使用/xx
       --%>
    <%--  绝对路径可以避免路径问题--%>
    <form action="<%=request.getContextPath()%>/processStep1" method="post">
      <table>
        <tbody>
          <tr>
            <th>书名</th>
            <th>购买</th>
          </tr>
          <%-- checkbox的name要一样，表示多选--%>
          <tr>
            <td>java</td>
            <td><input type="checkbox" name="book" value="java"></td>
          </tr>
          <tr>
            <td>html</td>
            <td><input type="checkbox" name="book" value="html"></td>
          </tr>
          <tr>
            <td>js</td>
            <td><input type="checkbox" name="book" value="js"></td>
          </tr>
          <tr>
            <td>css</td>
            <td><input type="checkbox" name="book" value="css"></td>
          </tr>
          <tr>
            <td colspan="2"><input type="submit" value="继续"></td>
          </tr>
        </tbody>
      </table>
    </form>
  </body>
</html>