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
                    -- META-INF                                     # META-INF
                        spring-configuration-metadata.json          # 配置元数据
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

### Group属性
groups数组包含的JSON对象可以由以下属性组成：

名称 | 类型 | 描述
---|---|---
name | String | group的全名，该属性是强制性的
type | String | group数据类型的类名。例如，如果group是基于一个被@ConfigurationProperties注解的类，该属性将包含该类的全限定名。如果基于一个@Bean方法，它将是该方法的返回类型。如果该类型未知，则该属性将被忽略
description | String | 一个简短的group描述，用于展示给用户。如果没有可用描述，该属性将被忽略。推荐使用一个简短的段落描述，第一行提供一个简洁的总结，最后一行以句号结尾
sourceType | String | 贡献该组的来源类名。例如，如果组基于一个被@ConfigurationProperties注解的@Bean方法，该属性将包含@Configuration类的全限定名，该类包含此方法。如果来源类型未知，则该属性将被忽略
sourceMethod | String | 	贡献该组的方法的全名（包含括号及参数类型）。例如，被@ConfigurationProperties注解的@Bean方法名。如果源方法未知，该属性将被忽略

### Property属性
properties数组中包含的JSON对象可由以下属性构成：

名称 | 类型 | 描述
---|---|---
name | String | property的全名，格式为小写虚线分割的形式（比如server.servlet-path）。该属性是强制性的
type | String | 	property数据类型的类名。例如java.lang.String。该属性可以用来指导用户他们可以输入值的类型。为了保持一致，原生类型使用它们的包装类代替，比如boolean变成了java.lang.Boolean。注意，这个类可能是个从一个字符串转换而来的复杂类型。如果类型未知则该属性会被忽略
description | String | 一个简短的组的描述，用于展示给用户。如果没有描述可用则该属性会被忽略。推荐使用一个简短的段落描述，开头提供一个简洁的总结，最后一行以句号结束
sourceType | String | 贡献property的来源类名。例如，如果property来自一个被@ConfigurationProperties注解的类，该属性将包括该类的全限定名。如果来源类型未知则该属性会被忽略
defaultValue | Object | 当property没有定义时使用的默认值。如果property类型是个数组则该属性也可以是个数组。如果默认值未知则该属性会被忽略
deprecated | String | 指定该property是否过期。如果该字段没有过期或该信息未知则该属性会被忽略

### hints属性
包含在hints数组中的JSON对象可以包含下表中显示的属性：

名称 | 类型 | 描述
---|---|---
name | String | 此提示引用的全名。名称以小写字母分隔的形式（例如server.servlet.path）。如果该属性引用一个Map（如system.contexts），则该提示将应用于Map的键（system.context.keys）或Map的值（system.context.values）。该属性是强制性的。
values | ValueHint [] | 由ValueHint对象定义的有效值列表（在下表中进行了介绍）。每个条目定义该值并可能有说明。
providers | ValueProvider [] | 由ValueProvider对象定义的提供者列表（在本文档稍后描述）。每个条目定义提供者的名称及其参数（如果有的话）。

包含在values每个hint元素的属性中的JSON对象可以包含下表中描述的属性：

名称 | 类型 | 描述
---|---|---
value | Object | 提示引用的元素的有效值。如果属性的类型是一个数组，它也可以是一个值的数组。该属性是强制性的。
description | String | 可以向用户显示的值的简短说明。如果没有可用的说明，可能会被忽略。建议描述为简短段落，第一行提供简明摘要。描述中的最后一行应以句点（.）结尾。

包含在providers每个hint元素的属性中的JSON对象可以包含下表中描述的属性：

名称 | 类型 | 描述
---|---|---
name | String | 提供程序的名称，用于为提示所引用的元素提供其他内容帮助。
parameters | JSON | 提供者支持的任何其他参数（查看提供者的文档以获取更多详细信息）。

**注: 具体配置元数据详情请参考官方文档**: [Spring Boot的附录：附录B. 配置元数据](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/html/configuration-metadata.html#configuration-metadata-annotation-processor)

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
