<%--
  Content: 学习session
--%>
<%--
  session在WEB开发环境中是指一类用来在客户端域服务器之间保持状态的解决方案，
  有时也指这种方案的存储结构.
  session机制采用的是在服务器端保持HTTP状态信息的方案
  服务器创建session出来后，会把session的id号，以cookie的形式回写给客户机，
  这样，只要客户机的浏览器不关，再去访问服务器时，都会带着session的id号去，
  服务器发现客户机浏览器带session id过来了，就会使用内存中与之对应的session为之服务。
  如果浏览器禁用了cookie可以采用url重写，把sessionid附加在url路径后面

// Httpsession的创建
  对于jsp，若当前的jsp是客户端访问的当前WEB应用的第一个资源，且jsp的page指定了session=false(禁用jsp隐含的session)
  则服务器就不会为jsp创建一个HttpSession对象，若当前jsp表示客户端访问的当前WEB应用的第一个资源，
  且其他页面已经创建了一个HttpSession对象，则当前jsp页面会返回一个会话的HttpSession对象，
  而不会创建一个新的HttpSession，
  对于servlet，若是客户端访问的第一个WEB应用的资源则只有调用request.getSession()或request.getSession(true)才会创建HttpSession对象

  在Servlet中获取HttpSession对象
  request.getSession(boolean create); 当create为false时，则若没有和当前页面关联的HttpSession对象就返回null，有就返回HttpSession对象
  若create为true时，则一定返回一个HttpSession对象，没有和当前页面关联的HttpSession对象就创建一个
  没有参数的getSession()与create为true一样
// Httpsession的销毁
  直接调用HttpSession的invalidate()方法
  WEB应用被卸载
  到过期时间自动销毁,可以设置最大存活时间，调用setMaxInactiveInterval(),或到xml中配置
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
  <head>
    <title>title</title>
  </head>
  <body>
    <p><%=session.getId()%></p>
    <%
      // 用cookie保持sessionid
      Cookie cookie = new Cookie("JSESSIONID",session.getId());
      cookie.setMaxAge(10);
      response.addCookie(cookie);
      //设置session的最大存活时间
      session.setMaxInactiveInterval(5); //单位为秒
      // 读取session的最大存活时间
      int maxInactiveInterval = session.getMaxInactiveInterval();
      out.print(maxInactiveInterval );
      // 使HttpSession失效
      session.invalidate();
    %>
  </body>
</html>
