# study

### 一、项目创建

![avatar](./picture/git项目创建.png)

1. 输入名称
2. 添加描述
3. 项目权限（公开或私有）
4. Add a README file 打钩(会生成README.md文件)
5. add .gitignore 打钩并且选择Maven(作用：git过滤不需要提交的文件)
6. Choose a license 打钩并且选择Apache License 2.0 (作用：开源协议说明)

### 二、linux

#### 2.1 安装docker

[安装官方文档](https://docs.docker.com/engine/install/centos/) 

1. 卸载旧版本

   ```
   sudo yum remove docker \
                     docker-client \
                     docker-client-latest \
                     docker-common \
                     docker-latest \
                     docker-latest-logrotate \
                     docker-logrotate \
                     docker-engine
   ```

2. 安装所需工具与安装地址

   ```
    sudo yum install -y yum-utils 
    
    sudo yum-config-manager \
       --add-repo \
       https://download.docker.com/linux/centos/docker-ce.repo
   ```

3. 安装*最新版本*的 Docker Engine 和 containerd

   ```
   sudo yum install docker-ce docker-ce-cli containerd.io
   ```

4. 启动 Docker

   ```
   systemctl start docker
   ```

5. 设置开机自动启动

   ```
   systemctl enable docker
   ```

6. 重启docker服务

   ```
   systemctl restart docker
   ```
   
7. 查看docker的运行状态

   ```
   systemctl status docker
   ```

#### 2.2 配置阿里云镜像加速

[阿里云官方文档](https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors) 

```
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://8z6fkgsn.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

#### 2.3 安装jdk1.8



#### 2.4 docker 命令

1. 查看正在运行的容器

   ```
   docker ps
   ```

2. 查看所有的容器(包括run、stop、exited状态的)

   ```
   docker ps -a
   ```

3. 只能删除没有在运行的容器

   ```
   docker rm 容器ID
   ```

4. 可以删除正在运行的容器

   ```
   docker rm -f 容器ID
   ```

5. 查看安装版本

   ```
   docker -v 
   ```

6. 查看docker已安装的镜像

   ```
   docker images
   ```

7. 拉取对应版本的镜像

   ```
   docker pull 镜像名称:版本号
   ```

8. 默认拉取最新的镜像

   ```
   docker pull 镜像名称
   ```

9. 启动容器

   ```
   docker start 容器ID
   ```

10. 终止容器

    ```
    docker stop 容器ID
    ```

11. 重启容器

    ```
    docker restart 容器ID
    ```

12. 删除镜像

    ```
    docker rmi 镜像名称 or ID
    ```
    
13. 开机启动容器

    ```
    docker update mysql --restart=always
    docker update redis --restart=always
    ```



#### 2.5 docker安装mysql

1. 查看可用的 MySQL 版本

   访问 MySQL 镜像库地址：https://hub.docker.com/_/mysql?tab=tags 。

   可以通过 Sort by 查看其他版本的 MySQL，默认是最新版本 **mysql:latest** 。

2. 下载镜像

   ```
   docker pull mysql:8.0.18
   ```

3. 创建挂载目录

   ```
   mkdir -p /home/app/mysql/conf
   mkdir -p /home/app/mysql/data
   mkdir -p /home/app/mysql/logs
   ```

4. 新建my.cnf配置文件

   ```
   vi /home/app/mysql/conf/my.cnf
   ```

   复制以下内容，为了解决中文乱码问题

   ```
   [mysqld]
   pid-file        = /var/run/mysqld/mysqld.pid
   socket          = /var/run/mysqld/mysqld.sock
   datadir         = /var/lib/mysql
   secure-file-priv= NULL
   # Disabling symbolic-links is recommended to prevent assorted security risks
   symbolic-links=0
   character-set-server=utf8 
   [client]
   default-character-set=utf8 
   [mysql]
   default-character-set=utf8 
   # Custom config should go here
   !includedir /etc/mysql/conf.d/
   ```

5. 创建启动容器

   ```
   docker run --restart=always -d -v /usr/local/mysql/conf/my.cnf:/etc/mysql/my.cnf -v /usr/local/mysql/logs:/logs -v /usr/local/mysql/data/mysql:/var/lib/mysql  -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 mysql:8.0.18
   ```

   参数解释

   ```
   --restart=always                                           -> 开机启动容器,容器异常自动重启
   -d                                                         -> 以守护进程的方式启动容器
   -v /usr/local/mysql/conf/my.cnf:/etc/mysql/my.cnf          -> 映射配置文件
   -v /usr/local/mysql/logs:/logs                             -> 映射日志
   -v /usr/local/mysql/data/mysql:/var/lib/mysql              -> 映射数据
   -p 3306:3306                                               -> 绑定宿主机端口
   --name mysql                                               -> 指定容器名称
   -e MYSQL_ROOT_PASSWORD=123456                              -> 写入配置root密码
   ```

6. 修改mysql

   ```
   #docker登录mysql
   docker exec -it mysql bash
   mysql -uroot -p 
   #查看mysql
   show databases;
   #使用mysql
   use mysql;
   #修改root用户加密规则
   alter user 'root'@'%' identified by 'root' password expire never;
   #修改root用户的加密规则
   alter user 'root'@'%' identified with mysql_native_password by'123456';
   #刷新权限
   flush privileges;
   #退出
   exit
   ```

7. 关闭防火墙或者开放端口

   ```
   #关闭防火墙
   systemctl stop firewalld.service
   ```


#### 2.6 docker安装redis

1. 下载镜像

   ```
   docker pull redis
   ```

2. 创建挂载目录

   ```
   mkdir -p /home/app/redis/conf
   mkdir -p /home/app/redis/data
   ```

3. 新建redis.conf配置文件

   ```
   touch /home/app/conf/redis.conf
   ```

   复制以下内容，解决redis持久化

   ```
   appendonly yes
   ```

4. 创建启动容器

   ```
   docker run -p 6379:6379 --name redis -v /home/app/redis/data:/data \
   -v /home/app/redis/conf/redis.conf:/etc/redis/redis.conf \
   -d redis redis-server /etc/redis/redis.conf
   ```

5. 开机自动启动容器

   ```
   docker update mysql --restart=always
   ```

#### 2.7 docker安装nacos

1. 下载镜像

   ```
   docker pull nacos/nacos-server:1.4.2
   ```

2. 创建挂载目录

   ```
   mkdir -p /home/app/nacos/data
   mkdir -p /home/app/nacos/logs
   mkdir -p /home/app/nacos/conf
   ```

3. 授权

   ```
   chmod 777 /home/app/nacos/data
   chmod 777 /home/app/nacos/logs
   chmod 777 /home/app/nacos/conf
   chmod 777 /home/app/nacos
   ```

4. 新建application.properties配置文件

   ```
   vi conf/application.properties
   ```

   复制以下内容，解决nacos配置

   ```
   
   ```

5. 创建启动容器

   ```
   docker run --name nacos -d -p 8848:8848 --privileged=true --restart=always -e JVM_XMS=256m -e JVM_XMX=256m -e MODE=standalone -e PREFER_HOST_MODE=hostname -v /home/app/nacos/logs:/home/nacos/logs -v /home/app/nacos/conf:/home/nacos/conf -v /home/app/nacos/data:/home/nacos/data nacos/nacos-server:1.4.2
   ```

6. 