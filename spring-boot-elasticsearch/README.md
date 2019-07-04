# Spring Boot Examples(2.x版本)

### 项目介绍
&emsp;&emsp;基于 Spring Boot(2.x版本) 实践学习案例集合，仅供学习参考！如有更好的方案和idea，欢迎互相交流！
如您觉得该项目对您有所帮助，欢迎点击右上方的Star标记，给予支持！！！谢谢 ~ ~

### 项目结构
注：我们在学习Spring Boot、Spring Cloud框架的时候，一定要知道它们的版本对应关系，以避免造成不必要的困恼与麻烦。   
(学习案例连续更新中...请Star和关注支持一下，随时获取最新动态！谢谢 ~ ~)

    spring-boot-examples                        # Spring Boot(2.x) 项目案例
        - examples-common                       # 项目公共资源 Module
        - spring-boot-configuration-metadata    # 项目公共资源 Module
        - spring-boot-docker                    # Docker部署Spring Boot项目
            -- spring-boot-docker-compose       # 基于Docker Compose部署项目
            -- spring-boot-dockerfile           # 基于Dockerfile部署项目
        - spring-boot-websocket                 # WebSocket Module
        - ...                                   # 待续
        pom.xml                                 # Maven 资源库配置文件

### 注意事项
1. 设置内置账号的密码
```bash
# 进入es容器
[root@localhost ~]# docker exec -it es-master /bin/bash
# 设置密码
[root@c2cacaf0f691 elasticsearch]# /usr/share/elasticsearch/bin/elasticsearch-setup-passwords interactive

Initiating the setup of passwords for reserved users elastic,apm_system,kibana,logstash_system,beats_system,remote_monitoring_user.
You will be prompted to enter passwords as the process progresses.
Please confirm that you would like to continue [y/N]y

Enter password for [elastic]: 
Reenter password for [elastic]: 
Enter password for [apm_system]: 
Reenter password for [apm_system]: 
Enter password for [kibana]: 
Reenter password for [kibana]: 
Enter password for [logstash_system]: 
Reenter password for [logstash_system]: 
Enter password for [beats_system]: 
Reenter password for [beats_system]: 
Enter password for [remote_monitoring_user]: 
Reenter password for [remote_monitoring_user]: 
Changed password for user [apm_system]
Changed password for user [kibana]
Changed password for user [logstash_system]
Changed password for user [beats_system]
Changed password for user [remote_monitoring_user]
Changed password for user [elastic]
```

2. es-head访问地址: http://192.168.0.10:9100/?auth_user=elastic&auth_password=123456

### 参与贡献
1. 2018年12月5日: 构建 spring-boot-examples [Module]
2. 2018年12月5日: 初始化 examples-common [Module]
3. 2018年12月5日: 初始化 spring-boot-websocket [Module]
4. 2019年06月1日: 构建 spring-boot-docker [Module]
5. 2019年06月1日: 初始化 spring-boot-dockerfile [Module]
6. 2019年06月2日: 初始化 spring-boot-docker-compose [Module]
7. 2019年06月3日: 优化 Maven pom.xml [文件]
8. 2019年06月8日: 初始化 spring-boot-configuration-metadata [Module]
9. 2019年06月9日: 更新 README.md [文档]

### 相关文章
#### 『 Spring Boot 2.x 快速教程 』
- [Spring Boot 整合 WebSocket](https://www.lmaye.com/2018/12/06/20181206163745/)

#### 『 Centos 7 快速教程 』
- [Centos 7 静态IP设置](https://www.lmaye.com/2017/12/22/20180809103359/)
- [Linux增加bash脚本为service，开机自启服务脚本配置](https://www.lmaye.com/2017/12/23/20180809103413/)
- [Centos7 安装 Docker CE](hhttps://www.lmaye.com/2019/04/28/20190428183357/)
- [Centos7 安装 JDK1.8](https://www.lmaye.com/2019/04/29/20190429005630/)
- [Centos7 安装较高版本Ruby2.2+（RVM 安装）](https://www.lmaye.com/2019/01/24/20190124223042/)
- [Centos7 开启Docker远程API访问端口](https://www.lmaye.com/2019/06/04/20190604230713/)

#### 『 Docker 快速教程 』
- [Docker 安装 MongoDB](https://www.lmaye.com/2019/05/06/20190506232452/)
- [Docker 安装 MySQL 8.0](https://www.lmaye.com/2019/05/22/20190522162930/)
- [Dockerfile 部署MySql 8并初始化数据脚本](https://www.lmaye.com/2019/06/02/20190602133656/)

#### 『 Redis 快速教程 』
- [Redis 配置文件详解](https://www.lmaye.com/2018/09/06/20180906002632/)
- [Redis Cluster 集群](https://www.lmaye.com/2019/01/24/20190124212849/)
- [Redis 配置集群遇到问题及解决方法](https://www.lmaye.com/2019/01/24/20190124223656/)

### 联系我
    * QQ: 379839355
    * QQ群: [Æ┊Java✍交流┊Æ](https://jq.qq.com/?_wv=1027&k=5Dqlg2L)
    * QQ群: [Cute Python](https://jq.qq.com/?_wv=1027&k=58hW2jl)
    * Email: lmay@lmaye.com
    * Home: [lmaye.com](https://www.lmaye.com)
    * GitHub: [lmayZhou](https://github.com/lmayZhou)
