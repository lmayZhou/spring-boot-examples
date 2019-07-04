# Spring Boot Elasticsearch(2.x版本)

#### 项目介绍
&emsp;&emsp;Spring Boot(2.x版本) 整合 Elasticsearch实例，简单的实现基本的ES索引操作;
WebSocket整合实践案例，仅供学习参考！如有更好的方案和idea，欢迎互相交流！如您觉得该项目对您有所帮助，欢迎点击右上方的Star标记，给予支持！！！谢谢 ~ ~

#### 项目结构
    spring-boot-elasticsearch                               # Elasticsearch Demo
        - spring-boot-elasticsearch                         # 配置和脚本文件目录
            docker-compose.yml                              # docker构建文件
            Dockerfile                                      # Dockerfile文件
            es-master.yml                                   # es主节点配置文件
            es-slave1.yml                                   # es从节点配置文件
            es-slave2.yml                                   # es从节点配置文件
            fields.yml                                      # filebeat模版
            filebeat.yml                                    # filebeat日志收集配置文件
            init.sh                                         # 项目脚本
            kibana.yml                                      # kibana配置文件
            nginx.conf                                      # nginx配置文件
        - src                                               # 源文件目录
            -- main                                         # 主目录
                -- java                                     # Java 源文件目录
                    -- com.lmaye.spring.boot.elasticsearch  # Java 包路径
                        -- config                           # 项目配置类
                        -- controller                       # 控制器
                        -- entity                           # 实体类
                        -- exception                        # 自定义异常
                        -- param                            # 参数类
                        -- repository                       # Repository
                        -- service                          # 服务层
                            -- impl                         # 服务实现类
                        SpringBootElasticsearchApplication  # 应用启动
                -- resources                                # 项目资源目录
                    application.yml                         # 项目配置文件
                    banner.txt                              # banner 文件
                    log4j2.xml                              # 日志配置文件
            -- test                                         # 测试目录
        pom.xml                                             # Maven 资源库配置文件

### 注意事项
1. 设置内置账号的密码（x-pack 收费此步骤忽略）
   es-head访问地址: http://192.168.0.10:9100/?auth_user=elastic&auth_password=123456
```$bash
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

2. 加载spring-data-elasticsearch:3.2.0.RC1资源包, 兼容ES:7.1.1版本的服务
```$xslt
<!-- 配置源 -->
<repositories>
    <repository>
        <id>spring-libs-snapshot</id>
        <name>Spring Snapshot Repository</name>
        <url>https://repo.spring.io/libs-snapshot</url>
    </repository>
</repositories>
```

3. spring-data-elasticsearch 3.2.0.RC1版本类型异常问题: long totalHits = response.getHits().getTotalHits();
   解决方法:
```$xslt
# 降低ES版本为6.8.0
<elasticsearch.version>6.8.0</elasticsearch.version>
```

4. 打包时引用本地项目异常提示: https://repo.spring.io/libs-snapshot找不到此jar包
   解决方法: <scope>provided</scope>
```$xslt
<dependency>
    <groupId>com.lmaye</groupId>
    <artifactId>examples-common</artifactId>
    <version>${project.version}</version>
    <!--
        compile: 编译依赖范围，默认使用该范围，对于编译、测试、运行三宗classpath都有效。
        test: 测试依赖范围，只对测试classpath有效
        provided: 已体提供依赖范围。对于编译和测试classpath有效，运行时无效
        runtime: 运行时依赖范围。对于测试和运行的claspath有效，编译无效
        system: 系统依赖范围。和provided依赖范围完全一致。使用system范围的依赖，不许通过systemPath元素显示地制定依赖文件的路径。
        import: 导入依赖范围。不会对三种classpath产生实际的影响。
    -->
    <scope>provided</scope>
</dependency>
```

5. Spring Boot 项目访问ES时报错: Caused by: io.netty.channel.AbstractChannel$AnnotatedNoRouteToHostException: No route to host: /192.168.0.10:9302
   解决方法: 
```$bash
# 防火墙开启ES TCP端口
[root@localhost ~]# firewall-cmd --zone=public --add-port=9300/tcp --permanent
[root@localhost ~]# firewall-cmd --zone=public --add-port=9301/tcp --permanent
[root@localhost ~]# firewall-cmd --zone=public --add-port=9302/tcp --permanent
# 重新载入
[root@localhost ~]# firewall-cmd --reload
```

### Elasticsearch
&emsp;&emsp;ElasticSearch是一个基于Lucene的搜索服务器。它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。<br/>
&emsp;&emsp;Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，
稳定，可靠，快速，安装使用方便。官方客户端在Java、.NET（C#）、PHP、Python、Apache Groovy、Ruby和许多其他语言中都是可用的。根据DB-Engines的排名显示，
Elasticsearch是最受欢迎的企业搜索引擎，其次是Apache Solr，也是基于Lucene。<br/>
&emsp;&emsp;ElasticSearch 是一个分布式、高扩展、高实时的搜索与数据分析引擎。它能很方便的使大量数据具有搜索、分析和探索的能力。充分利用ElasticSearch的水平伸缩性，
能使数据在生产环境变得更有价值。ElasticSearch 的实现原理主要分为以下几个步骤，首先用户将数据提交到Elastic Search 数据库中，再通过分词控制器去将对应的语句分词，
将其权重和分词结果一并存入数据，当用户搜索数据时候，再根据权重将结果排名，打分，再将返回结果呈现给用户。

### 参与贡献
1. 2019年06月11日: 初始化项目
2. 2019年06月13日: 初始化 spring-boot-elasticsearch
3. 2019年06月20日: 实现ES功能
4. 2019年07月01日: 配置脚本
5. 2019年07月05日: 更新 README.md [文档]

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
