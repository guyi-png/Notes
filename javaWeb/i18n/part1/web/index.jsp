<%@ page import="java.util.Locale" %>
<%--
  User: 古乂
  Date: 2020/8/13
  Time: 14:57
  Content: 国际化

  国际化 & 本地化
  国际化: 是指程序在不做任何修改的情况下，就可以在不同的国家或地区的不同语言环境下，
  按照当地的语言和格式习惯显示字符。国际化又称为i18n（internationalization）。

  本地化：一个国际化的程序，当他运行在本地机器上时，需要根据本地机器的语言和地区设置显示相应的字符，
  这个过程叫本地化，又称为L10N（Localization）

  本地敏感数据：随用户区域信息而变化的数据成为本地敏感数据，如数字，货币，日期， 时间等数据
  相关的API：
      DateFormat, SimpleDateFormat
      NumberFormat
      MessageFormat
      ResourceBundle
      Locale
  国际化资源文件相关注意事项：
      properties文件格式，必须提供base.properties文件和base_languageCode_countryCode.properties文件
      资源文件的key必须相同，可能需要将非字母非数字的字符转为ascii编码

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>俺的网站</title>
  </head>
  <body>
    <!--getLocale获取地点-->
    <%=
      request.getLocale()
    %>
  </body>
</html>
