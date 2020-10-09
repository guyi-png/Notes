# 只要学不死， 就往死里学

### docker官网
[docker](https://www.docker.com/)
       Docker 是一个开源的应用容器引擎，
       让开发者可以打包他们的应用以及依赖包到一个可移植的镜像中，
       然后发布到任何流行的 Linux或Windows 机器上，
       也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口。
       
### docker的名词 
镜像(image): 好比是一个模板，可以通过这个模板创建容器服务
容器(container)： 独立运行一个或一组应用， 通过镜像来创建
仓库(repository)： 存放镜像的地方

## docker的常用命令
### 帮助命令：
docker version  显示docker的版本信息
docker info   显示docker的系统信息
docker [keywords] --help
### 镜像命令
docker images 查看所有本地主机的镜像
    REPOSITORY： 镜像的仓库源
    TAG： 镜像的标签
    IMAGE ID： 镜像id
    CREATED： 创建时间
    SIZE： 镜像的大小
    选项：
          -a, --all             Show all images (default hides intermediate images)
              --digests         Show digests
          -f, --filter filter   Filter output based on conditions provided
              --format string   Pretty-print images using a Go template
              --no-trunc        Don't truncate output
          -q, --quiet           Only show numeric IDs
          
docker search 镜像名 搜索镜像

docker pull:[版本号] 镜像名     下载镜像

docker rmi -f IMAGE ID/REPOSITORY  删除镜像
docker rmi -f $(docker images -aq)   删除全部镜像

### 容器命令
有了镜像才可以创建容器，下载个centos镜像
docker pull centos

新建容器并启动
docker run [选项] image
常用选项：   
    --name="name" 给容器分配一个名字
    -d  在后台运行
    -it 使用交互方式运行，进入容器查看内容
    -p  指定容器的端口， -p 主机端口:容器端口
    -P 随机指定端口
    -e 环境配置
    
sudo docker run -it centos /bin/bash  启动并进入容器

exit 退出容器
ctrl+p+q 容器不停止退出

docker ps 列出当前运行的容器
docker ps -a 列出所有运行的容器，包括曾经运行的容器
docker ps -a -n=[number] 显示最近的number个容器记录
docker ps -q 显示容器id(CONTAINER ID)

docker rm 容器id   删除容器
docker rm -f $(docker ps -aq)  删除所有的容器

docker start 容器id 启动某个容器
docker stop 容器id  停止当前某个容器运行
docker restart 容器id  重启某个容器
docker kill 容器id  强制停止某个容器

### 常用的其他命令
docker run -d cnetos  后台启动容器,注意一定要有前台进程对应，在后台开启容器但没有前台应用就会自动停止

docker logs -tf --tail 容器     查看日志

docker top 容器id  查看容器内的进程信息

docker inspect 容器id   查看容器的元数据
docker image inspect 镜像id   查看镜像的id

#### 进入当前运行的容器
docker exec -it 容器id bashshell
docker attach 容器id

上面两种进入容器命令的区别： exec 是进入容器并开启一个新的终端，可以在里面进行操作，
attach 进入容器正在执行的终端中，不会开启一个新的终端

#### 从容器内copy文件到主机上
docker cp 容器id：容器内路径 目标主机路径


sudo docker run -it --rm tomcat:8.5  运行一次性的测试容器

docker stats 查看cpu状态

### docker 镜像是什么
镜像是一种轻量级的可执行的独立软件包，用来打包软件运行环境和基于运行环境开发的软件，
它包含运行某个软件所需的所有内容，包括代码，运行时，库，环境变量和配置文件

如何得到镜像： 从远程仓库下载，别人给的， 自己制作

### commit 镜像
docker commit 提交容器成为一个新的版本

docker commit -m="提交的描述信息" -a="作者" 容器id 目标镜像名:[tag]

## 容器数据卷
如果数据都在容器中，删除容器会丢失数据
卷技术就是让docker容器中产生的数据，同步到本地，完成容器的持久化和同步操作
并且容器之间也可以数据共享

### 使用数据卷(v volume卷， 将容器对应的目录挂载到主机目录上)
docker run -it -v 主机目录：容器目录:(ro/rw)

docker volume ls 列出所有的卷

#### 具名和匿名挂载
匿名挂载即只指定容器内目录
具名挂载: -v 名：容器内目录
通过 docker volume inspect 名   查看挂载到主机的那个目录上
默认卷的主机目录：  var/lib/docker/volume/名/-data

#### 数据卷容器
sudo docker run -it --name docker2 --volumes-from docker1 iamge:tag  
（docker1和docker2都是容器）将docker2挂载到docker1上，docker1的 卷数据 就可以同步到docker2
（--volumes-from 可以理解为构成继承关系？）
容器间是互相copy的，就算删除docker1，docker2还能访问卷数据

## DockerFile
DockerFile用于构建 docker镜像的 构建文件，可以自己制作一个镜像
通过脚本生成镜像， 脚本的一个一个命令对应镜像的一层一层

dockerfile中每个关键字都要大写，执行从上到下，'#'表示注释
每一个指令都会创建一个镜像层，并提交

dockerfile的指令：
      指令                            说明
      FROM                   设置镜像使用的基础镜像
      MAINTAINER                设置镜像的作者  
      RUN                     编译镜像时运行的脚本
      CMD                      设置容器的启动命令
      LABEL                      设置镜像的标签
      EXPOSE                  设置镜像暴露的端口
      ENV                      设置容器的环境变量
      ADD                    编译镜像时复制文件到镜像中
      COPY                   编译镜像时复制文件到镜像中
      ENTRYPOINT                设置容器的入口程序
      VOLUME                     设置容器的挂载卷
      USER               设置运行RUN CMD ENTRYPOINT的用户名
      WORKDIR         设置RUN CMD ENTRYPOINT COPY ADD指令的工作目录
      ARG                   设置编译镜像时加入的参数
      ONBUILD               设置镜像的ONBUILD指令


eg.脚本（卷为匿名挂载）：
FROM centos
VOLUME ["volume1", "volume2"]
CMD echo "--ok--"
CMD /bin/bash

sudo docker build -f 指定的脚本  -t 镜像名:tag .     创建镜像
docker history iamge_id   查看镜像的构建过程

### 发布自己的镜像到dockerhub上或阿里云上
docker login -u username -p password   登录dockerhub
docker logout 退出登录
docker push username/image:tag   发布镜像

### 将image打包压缩/解压
docker save -o  文件
docker load -i  文件


## Docker network
sudo docker exec 容器 ip addr    查看容器的内部ip地址

容器启动时得到一个 eth0@if7 ip地址
可以ping 容器ip地址   查看是否联通

每启动一个docker容器时，docker就会给docker容器分配一个IP
只要安装了docker，就会有一个网卡， docker0桥接模式，使用的技术是veth-pair
veth-pair 就是一对的虚拟设备接口，和 tap/tun 设备不同的是，它都是成对出现的。一端连着协议栈，一端彼此相连着。
容器与容器之间是可以互相ping通的：容器与容器之间都是默认以docker0做路由进行桥接

**sudo docker run -d -P --name=容器2 --link 容器1 tomcat     通过 --link 容器  使容器2单方面网络连通到容器1**
--link  就是在容器的/etc/hosts文件中配置了： 另一个容器的ip  容器名  容器id

**--link方式有局限（ip改变很麻烦），docker0不支持容器名连接**

### 容器互连(通过自定义docker网络使容器可以通过容器名连接)
docker network ls 查看docker所有网络信息

docker network create --driver bridge --subnet 192.168.0.0/16 --gateway 192.168.0.1 'networkName'     自定义docker网络

--net 'networkName' 指定网络模式

### 网络连通
docker network connect 'networkName' 容器名     连接一个容器到一个网络上，这样这个容器就可以和该网络的其他容器连接




### Dcoker compose

### Docker Swarm

es nginx jenkins 计算机网络原理
