<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  User: 古乂
  Date: 2020/7/28
  Time: 13:13
  Content:  EL的隐藏对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    EL的隐藏对象:
        pageScope, requestScope, sessionScope, applicationScope代表几个域,用法一样
        与输入有关的隐藏对象： param , paramValues
        其他隐含对象： cookie, header, headerValues, initParam, pageContext
--%>
    <!-- param, paramValues-->
    ${param.name}  <!--与request.getParameter("name")一样-->
    ${paramValues.age[0]} <!--与request.getParameterValues("age")[0]-->
    ${param.name.charAt(1)} <!--request.getAttribute("name").getCharAt(1)-->
    ${param.name["class"].name} <!--name.class.getName()-->
    <!-- cookie-->
    ${cookie.JSESSIONID.name}
    ${cookie.JSESSIONID.value}
    <!--header获取请求或响应头部的值-->
    ${header["Accept-Language"]}
    ${header["Connection"]}
    <!-- initParam获取web.xml中context-param的配置参数-->
    ${initParam.initName}
    <!--pageContext-->
    ${pageContext.request.contextPath}
<%=((HttpServletRequest)pageContext.getRequest()).getContextPath()%><!--两者结果一样-->
    <%--el中使用三元运算符 --%>
    ${"null"==null? "yes":"no"}
    <%-- el中的 empty ，可以检查集合是否存在或是否为空 ，否true，是false --%>
    <%
        List<String> list = new ArrayList<>();
        list.add("olg");
        request.setAttribute("list", list);
    %>
    ${empty requestScope.list} <!--false-->
    ${empty requestScope.list1}<!--true-->
</body>
</html>
