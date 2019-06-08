# Spring Boot Configuration Metadata(2.x版本)

#### 项目介绍
&emsp;&emsp;Spring Boot(2.x版本) 整合 Configuration Metadata(自定义配置元数据)实例，简单的实现基本的yml/properties配置元数据信息;
WebSocket整合实践案例，仅供学习参考！如有更好的方案和idea，欢迎互相交流！如您觉得该项目对您有所帮助，欢迎点击右上方的Star标记，给予支持！！！谢谢 ~ ~

#### 项目结构
    spring-boot-configuration-metadata                              # Customize Configuration Metadata Demo
        - src                                                       # 源文件目录
            -- main                                                 # 主目录
                -- java                                             # Java 源文件目录
                    -- com.lmaye.spring.boot.configuration.metadata # Java 包路径
                        -- properties                               # 配置属性
                        SpringBootWebsocketApplication              # 应用启动
                -- resources                                        # 项目资源目录
                    application.yml                                 # 项目配置文件
            -- test                                                 # 测试目录
        pom.xml                                                     # Maven 资源库配置文件

### Configuration Metadata(配置元数据)
&emsp;&emsp;Spring Boot jars包含元数据文件，它们提供了所有支持的配置属性详情。这些文件设计用于让IDE开发者能够为使用application.properties或application.yml文件的用户提供上下文帮助及代码完成功能。
主要的元数据文件是在编译器通过处理所有被@ConfigurationProperties注解的节点来自动生成的。

### Configuration Metadata Format(元数据格式)
配置元数据位于jars文件中的META-INF/spring-configuration-metadata.json，它们使用一个具有"groups"或"properties"分类节点的简单JSON格式：
```$xslt
{"groups": [
    {
        "name": "server",
        "type": "org.springframework.boot.autoconfigure.web.ServerProperties",
        "sourceType": "org.springframework.boot.autoconfigure.web.ServerProperties"
    },
    {
        "name": "spring.jpa.hibernate",
        "type": "org.springframework.boot.autoconfigure.orm.jpa.JpaProperties$Hibernate",
        "sourceType": "org.springframework.boot.autoconfigure.orm.jpa.JpaProperties",
        "sourceMethod": "getHibernate()"
    }
    ...
],"properties": [
    {
        "name": "server.port",
        "type": "java.lang.Integer",
        "sourceType": "org.springframework.boot.autoconfigure.web.ServerProperties"
    },
    {
        "name": "server.servlet-path",
        "type": "java.lang.String",
        "sourceType": "org.springframework.boot.autoconfigure.web.ServerProperties",
        "defaultValue": "/"
    },
    {
          "name": "spring.jpa.hibernate.ddl-auto",
          "type": "java.lang.String",
          "description": "DDL mode. This is actually a shortcut for the \"hibernate.hbm2ddl.auto\" property.",
          "sourceType": "org.springframework.boot.autoconfigure.orm.jpa.JpaProperties$Hibernate"
    }
    ...
],"hints": [
    {
        "name": "spring.jpa.hibernate.ddl-auto",
        "values": [
            {
                "value": "none",
                "description": "Disable DDL handling."
            },
            {
                "value": "validate",
                "description": "Validate the schema, make no changes to the database."
            },
            {
                "value": "update",
                "description": "Update the schema if necessary."
            },
            {
                "value": "create",
                "description": "Create the schema and destroy previous data."
            },
            {
                "value": "create-drop",
                "description": "Create and then destroy the schema at the end of the session."
            }
        ]
    }
]}
```
每个"property"是一个配置节点，用户可以使用特定的值指定它。例如，server.port和server.servlet-path可能在application.properties中如以下定义：
```$xslt
Yaml Config:
---------------------------------
blog:
  host: https://www.lmaye.com
  port: 80
  email: lmay@lmaye.com

Properties Config:
---------------------------------
blog.host: https://www.lmaye.com
blog.port: 80
blog.email: lmay@lmaye.com
```
"groups"是高级别的节点，它们本身不指定一个值，但为properties提供一个有上下文关联的分组。例如，server.port和server.servlet-path属性是server组的一部分。

注：不需要每个"property"都有一个"group"，一些属性可以以自己的形式存在。

#### 参与贡献
1. 2018年12月5日: 初始化项目
2. 2019年06月8日: 初始化 spring-boot-configuration-metadata
3. 2019年06月8日: 实现Configuration Metadata自动配置功能
4. 2019年06月9日: 更新 README.md [文档]

#### 联系我
    * QQ: 379839355
    * QQ群: [Æ┊Java✍交流┊Æ](https://jq.qq.com/?_wv=1027&k=5Dqlg2L)
    * QQ群: [Cute Python](https://jq.qq.com/?_wv=1027&k=58hW2jl)
    * Email: lmay@lmaye.com
    * Home: [lmaye.com](https://www.lmaye.com)
    * GitHub: [lmayZhou](https://github.com/lmayZhou)
