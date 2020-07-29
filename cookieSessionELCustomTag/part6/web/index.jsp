<%@ page import="java.util.List" %>
<%@ page import="com.custom.customer.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  User: 古乂
  Date: 2020/7/28
  Time: 14:50
  Content: 使用自定义标签
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="olg" uri="http://www.test.com/hello/core" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <%--使用自定义标签，返回最大值 --%>
<%--    <olg:max num1="${param.a}" num2="${param.b}"/>--%>
    <%--使用自定义标签，读取指定文件--%>
<%--    <olg:readFile src="WEB-INF/lyric.txt"/>--%>
    <%--使用自定义标签，获取标签体的内容--%>
    <olg:testFragment>
      ツキアカリ (月光) - Rie Fu (リエ フゥ)
      词：Rie fu
      曲：Rie fu
      青い青い空に
      月の光をともす
      甘く淡く重い
      そんなものに捉われて
      この月明かりの下
      ひとり知れず
      君の名前だけを呼んでいた
      いつまでも未来をさがしてた
      この光の中に
      いつもいつもそばで
      信じてゆく力が
      遠く脆いものを
      動かしてる気がしてた
      この月明かりの下ひとり知れず
      君の名前だけを呼んでいた
      静かな愛情を信じてた
      この光の中に
      何も掴めないような夜には
      君を想わないときはない
      There isn't a day I don't think about
      迷う心が君に届くように
      この月明かりの下で
      私の名前を呼んで
      たしかに逢いにゆくよ
      どこでも
      君のそばに
      この月明かり瞬きひとつせず
      静かに私を見つめていた
      君との未来をさがしてた
      この光の中に
    </olg:testFragment>
    <%
      List<Customer>  customers = new ArrayList<>();
      customers.add(new Customer("olg",19,"asfhtj"));
      customers.add(new Customer("gfd",99,"idghgj"));
      customers.add(new Customer("ohf",19,"sfhtgj"));
      customers.add(new Customer("dhx",19,"tjgjnl"));
      request.setAttribute("customers", customers);

      Map<String, Customer> customerMap = new HashMap<>();
      customerMap.put("one", customers.get(0));
      customerMap.put("two", customers.get(0));
      customerMap.put("three", customers.get(0));
      customerMap.put("four", customers.get(0));
      request.setAttribute("customerMap", customerMap);
    %>
    <br>
     <!--自定义foreach-->
    <olg:forEach items="${requestScope.customerMap}" var="cust">
      ${pageScope.cust.key}---${pageScope.cust.value.name}---${pageScope.cust.value.age}---${cust.value.address}
    </olg:forEach>
    <br>
    <olg:forEach items="${requestScope.customers}" var="cust">
      ${cust.name}===${cust.age}===${cust.address}
    </olg:forEach>
  </body>
</html>
