# JavaWeb应用的概念
    javaWeb应用由一组Servlet，HTML页，类，以及其他可以被绑定的资源构成
# JavaWeb应用中可以包含的内容：
    - Servlet
    - jsp(java server page)
    - 实用类
    - 静态文档html，图片等
    - 描述Web应用的信息(web.xml)
    
## Servlet容器:
    为JavaWeb应用提供运行时环境
    负责管理Servlet和JSP的生命周期
    以及管理它们的共享数据
### 流行的Servlet容器软件：
    - Tomcat
    - Resin
    - J2EE服务器中也提供了内置的Servlet容器
    
#### 部署并启动tomcat服务器
    1. 到官网指定版本在core中找文件并下载
    2. 解压apache-tomcat-xx.zip
    3. 配置环境变量java_home
    4. 找到apache-tomcat-xx/bin/startup.bat，双击启动服务器
    5. 浏览器中输入localhost:8080查看是否开启服务
    6. 若已经启动一个tomcat应用，若在开启同一个Tomcat应用，会抛出一个异常
    7. shutdown.bat关闭服务器
    8. 可以修改conf目录下的server.xml文件的端口号
    
#### 在环境变量path中添加apache-tomcat-xx/bin的路径，然后添加环境变量CATALINA_HOME值为apache-tomcat-xx的路径
    任意路径打开cmd输入startup开启服务
    shutdown关闭服务
    也可以 catalina start 开启服务
    catalina stop 关闭服务
    catalina run 运行服务
    
## Tomcat提供一个管理程序：
    manager， 用于部署到Tomcat服务器中的web应用程序
    要访问manager web应用程序，需要添加具有管理员权限的账号。
    在tomcat-users.xml文件中添加manager角色
      <role rolename="manager-gui"/>
      <user username="tomcat" password="tomcat" roles="manager-gui"/>
    
## 在Java项目下创建web开发的目录结构
    - WebContent(随意)
        - WEB-INF
            - classes(编译后的.class文件必须放到这)
            - lib
            - web.xml
        html(文件)
        jsp(文件)
        图片...
## 配置
    将src目录下的Java编译后的.class文件编译到WebContent/WEB-INF/classes中
    在apache-tomcat-xx/conf/Catalina/localhost中添加xml文件
    可在ide中更简单的配置
    
## Servlet的第一个实例
    1. 创建一个类实现Servlet接口，实现所有方法
    2. 在web.xml文件中配置和映射这个Servlet
        在web.xml文件中配置和映射:
            <servlet>
                   <servlet-name>FirstServlet</servlet-name>
                   <servlet-class>FirstServlet</servlet-class>
            </servlet>
            <servlet-mapping>
                   <servlet-name>FirstServlet</servlet-name>
                   <url-pattern>/first</url-pattern>
            </servlet-mapping>
            
### Servlet容器，tomcat为例
    1. 可以创建Servlet，并调用Servlet的相关生命周期函数
    2. 是运行Servlet， JSP， Filter等的软件环境
    
### Servlet的生命周期方法，只能是Servlet容器来调用
    - constructor：只有第一次请求Servlet时，创建Servlet的实例，调用构造函数
    - init： 也只调用一次，在创建好实例后，用于初始化Servlet
    - service： 用于响应请求，每次请求都会调用service
    - destroy： 只调用一次，在当前Servlet所在的WEB应用被卸载前调用，用于释放当前Servlet占用的资源
#### load-on-startup
    可以指定Servlet被创建的时机
    若负数，则在第一次请求时被创建
    若0或正数，则在当前WEB应用被Servlet容器加载时创建实例，
    且数越小越早创建
#### init方法的参数ServletConfig的方法
   > getInitParameter(String name): 获取指定参数名的初始化参数
   > getInitParameterNames(): 获取参数名组成的Enumerate对象
   > getServletName(): 获取Servlet的名字
   > getServletContext(): 获取ServletContext，获取到的它可以认为是WEB应用的一个大管家
    > 可以从它之中获取当前WEB应用的所有信息：
        > 1. 获取当前WEB应用的初始化参数
        > 2. 获取当前WEB应用的某个文件的绝对路径，部署后的路径
        > 3. 获取当前WEb应用的名称
# 中文乱码问题解决
>  保证contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"以及浏览器的编码集也要保持一致
>  对于post的请求数据，在获取信息前调用request.setCharacterEncoding("UTF-8")
>  对于get的请求数据, 可以通过修改Tomcat服务器的server.xml文件，在其中的某标签添加useBodyEncodingForURI="true"
