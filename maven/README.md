### maven
[Maven官网](http://maven.apache.org/)

    maven 是一款服务于Java平台的自动化构建工具
    发展： make -> ant -> maven -> gradle
    构建： 
        概念： 以 java源文件，框架配置的文件，jsp，html，图片等作为源材料
        去"生产"一个可以运行的项目的过程。
        构建过程中的各个环节：
            1. 清理： 将以前编译得到的旧的class字节码文件删除， 为下一次编译做准备
            2. 编译： 将Java的源程序编译成class的字节码文件
            3. 测试： 自动测试， 自动调用Junit程序
            4. 报告： 测试程序执行的结果
            5. 打包： 动态WEB工程打war包， java工程打jar包
            6. 安装： 将打包得到的文件复制到'仓库'中的指定位置
            7. 部署： 将动态的WEB工程生成的war包复制到指定的Servlet容器的指定目录下，使其可以运行
    安装Maven的步骤：
   [到Maven官网下载](http://maven.apache.org/download.cgi)
        
        1. maven由Java编写，需要要Java环境
        2. 去下载Maven的核心程序 
        3. 配置Maven， 解压Maven核心程序指定目录，
        去环境变量中创建 M2_HOME变量其值为那个解压后的目录，
        在path配置中加入 %M2_HOME%\bin
        4. 检验一下是否成功命令行键入 mvn -v
        
#### Maven的核心概念
   1. 约定的目录结构
   2. POM
   3. 坐标
   4. 依赖
   5. 仓库
   6. 生命周期/插件/目标
   7. 继承
   8. 聚合
   
##### 创建一个Maven工程
    1. 创建约定的目录结构
        工程名
            - src 源码
                - main  主程序
                    - java java的源码
                    - resources 放一些框架或其他工具的配置文件
                - test 测试程序
                    - java
                    resource
            - pom.xml Maven的配置文件
    这样配置是因为 Maven自动构建需要找到对应的文件。
    
    2. 常见的命令
        执行与构建过程相关的Maven命令时，必须进入到pom.xml文件目录下
        1. mvn clean 清理
        2. mvn compile 编译主程序
        3. mvn test-compile 编译测试程序
        4. mvn test 执行测试程序
        5. mvn package 打包
        6. mvn install 安装
        7. mvn site 生成站点 
        
##### pom (project object model)
    pom.xml对于maven工程是核心的配置文件，
     与构建过程相关的一切设置都在这个文件中进行设置
     
##### 坐标
    使用三个向量去唯一的定位一个Maven工程
        1. groupid  公司或组织的域名倒叙+项目名
        2. artifactid 模块名
        3. version 版本
    Maven工程的坐标与仓库中的路径的对应关系
        三坐标以'/连接' + /模块名.版本.jar
        如 org/springframework/spring-core/4.0.0.RELEASE/spring-core.4.0.0.RELEASE.jar
        
##### 仓库
    仓库的分类：
        本地仓库： 当前的电脑上部署的仓库目录，为当前的电脑上所有的Maven工程服务
        远程仓库： 
            私服： 搭建在局域网环境中，为局域网范围内的Maven工程服务
            中央仓库： 架设在Internet上，为全世界所有的Maven工程服务
            中央仓库的镜像： 为分担中央仓库的流量，提高用户的访问速度
    仓库的内容： Maven工程
        maven自身所需要的插件
        第三方框架或工具的jar包
        自己开发的Maven工程
##### 依赖
    Maven解析依赖信息时会到本地仓库中查找依赖的jar包
        对于我们自己开发的Maven工程， 使用 mvn install 命令安装后就可以进入仓库
    依赖的范围(scope)
                     对主程序有效     对测试程序有效     是否参与打包
        compile         yes               yes            yes
        test            no                yes            no
        provided        yes               yes            no
    依赖有传递性
    依赖的原则：
        作用： 解决模块工程之间的jar包冲突问题
        原则一： 验证路径最短者优先
        原则二： 验证路径相同时先声明者优先
        

##### 生命周期
    Maven 有以下三个标准的生命周期：    
        clean：项目清理的处理
            当我们执行 mvn post-clean 命令时，Maven 调用 clean 生命周期，它包含以下阶段：
            pre-clean：执行一些需要在clean之前完成的工作
            clean：移除所有上一次构建生成的文件
            post-clean：执行一些需要在clean之后立刻完成的工作
        default(或 build)：项目部署的处理： 
            validate（校验）:校验项目是否正确并且所有必要的信息可以完成项目的构建过程。
            initialize（初始化）:初始化构建状态，比如设置属性值。
            generate-sources（生成源代码）:	生成包含在编译阶段中的任何源代码。
            process-sources（处理源代码）:处理源代码，比如说，过滤任意值。
            generate-resources（生成资源文件）:生成将会包含在项目包中的资源文件。
            process-resources （处理资源文件）:复制和处理资源到目标目录，为打包阶段最好准备。
            compile（编译）:	编译项目的源代码。
            process-classes（处理类文件）:处理编译生成的文件，比如说对Java class文件做字节码改善优化。
            generate-test-sources（生成测试源代码）:生成包含在编译阶段中的任何测试源代码。
            process-test-sources（处理测试源代码）:处理测试源代码，比如说，过滤任意值。
            generate-test-resources（生成测试资源文件）:为测试创建资源文件。
            process-test-resources（处理测试资源文件）:复制和处理测试资源到目标目录。
            test-compile（编译测试源码）:	编译测试源代码到测试目标目录.
            process-test-classes（处理测试类文件）:处理测试源码编译生成的文件。
            test（测试）:使用合适的单元测试框架运行测试（Juint是其中之一）。
            prepare-package（准备打包）:在实际打包之前，执行任何的必要的操作为打包做准备。
            package（打包）:	将编译后的代码打包成可分发格式的文件，比如JAR、WAR或者EAR文件。
            pre-integration-test（集成测试前）:在执行集成测试前进行必要的动作。比如说，搭建需要的环境。
            integration-test（集成测试）:处理和部署项目到可以运行集成测试环境中。
            post-integration-test（集成测试后）:	在执行集成测试完成后进行必要的动作。比如说，清理集成测试环境。
            verify （验证）:	运行任意的检查来验证项目包有效且达到质量标准。
            install（安装）:	安装项目包到本地仓库，这样项目包可以用作其他本地项目的依赖。
            deploy（部署）:	将最终的项目包复制到远程仓库中与其他开发者和项目共享。
        site：项目站点文档创建的处理
            Maven Site 插件一般用来创建新的报告文档、部署站点等。
            pre-site：执行一些需要在生成站点文档之前完成的工作
            site：生成项目的站点文档
            post-site： 执行一些需要在生成站点文档之后完成的工作，并且为部署做准备
            site-deploy：将生成的站点文档部署到特定的服务器上
    
    各个构建环节执行的顺序： 不能打乱顺序，必须按照指定的顺序
    maven的核心程序中定义了抽象的生命周期，生命周期中各个阶段的具体任务是由插件来完成的
    maven核心程序为了更好的实现自动化构建，不管要执行生命周期中的哪个阶段，都会从生命周期的最初阶段开始
    
##### 插件和目标
    生命周期的各个阶段仅仅定义了要执行的任务是什么
    各个阶段和插件的目标是对应的
    相似的目标由特定的插件来完成

##### 统一管理依赖的版本
    同一框架的jar包版本要统一，统一更新时
    使用properties内使用自定义标签统一声明版本号,通过${}引用
    
##### 继承
    问题：由于test范围的依赖不能传递，所以必然会分散在各个模块工程中，很容易造成版本不一致
    需求： 统一管理各个模块工程中junit依赖的版本
    解决思路： 将junit依赖版本统一提取到"父"工程中，在子工程中声明依赖时不指定版本
    操作步骤： 创建一个maven工程作为父工程，打包方式为pom，在子工程中声明对父工程的引用
        将子工程的坐标中与父工程坐标中重复的内容删除，在父工程中统一junit的依赖，在子工程中删除junit的版本号
    配置完父工程后，在执行 install 命令时要先安装父工程
    
###### 聚合
    作用： 一键安装各个模块工程
    配置方式： 在一个总的‘聚合’工程中配置各个参与聚合的模块    
        <modules>
            <module></module>
            ...
        </modules>