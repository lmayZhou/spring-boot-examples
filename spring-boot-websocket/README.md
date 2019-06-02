# spring-boot-websocket

#### 项目介绍
&emsp;&emsp;spring-boot-websocket 属于Spring Boot集成WebSocket实例，简单的实现基本的功能演示;
项目学习案例，仅供参考！如有更好的方案和想法，欢迎互相交流！

#### 项目结构
    spring-boot-websocket                       # WebSocket Demo
        - src                                   # 源文件目录
            -- main                             # 主目录
                -- java                         # Java 源文件目录
                    -- com.lmay.websocket       # Java 包路径
                        -- config               # 项目配置类
                        -- controller           # 控制器
                        -- exception            # 自定义异常
                        -- handler              # 处理器
                        -- service              # 服务层
                            -- impl             # 服务实现类
                        -- task                 # Spring 定时任务
                        WebSocketApplication    # 应用启动
                -- resources                    # 项目资源目录
                    -- static                   # 静态资源
                    -- templates                # HTML模版
                    application.yml             # 项目配置文件
                    log4j2.xml                  # 日志配置文件
            -- test                             # 测试目录
        pom.xml                                 # Maven 资源库配置文件

#### 项目架构
1. JDK 8
2. Spring Boot 2
3. Maven
4. Guava
5. Gson
6. lombok
7. log4j2
8. Spring Scheduled
9. ...

### WebSocket
&emsp;&emsp;WebSocket 是 HTML5 开始提供的一种在单个 TCP 连接上进行全双工通讯的协议。<br/>
WebSocket 使得客户端和服务器之间的数据交换变得更加简单，允许服务端主动向客户端推送数据。在 WebSocket API 中，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输。<br/>
在 WebSocket API 中，浏览器和服务器只需要做一个握手的动作，然后，浏览器和服务器之间就形成了一条快速通道。两者之间就直接可以数据互相传送。

![websocket](https://www.lmaye.com/group1/M00/00/00/CmiBTlwIzWaAXMCoAAAuXGvteXc584.png)

&emsp;&emsp;浏览器通过 JavaScript 向服务器发出建立 WebSocket 连接的请求，连接建立以后，客户端和服务器端就可以通过 TCP 连接直接交换数据。
当你获取 Web Socket 连接后，你可以通过 send() 方法来向服务器发送数据，并通过 onmessage 事件来接收服务器返回的数据。
以下 API 用于创建 WebSocket 对象。

    var webSocket = new WebSocket(url, [protocol] );
    
以上代码中的第一个参数 url, 指定连接的 URL。第二个参数 protocol 是可选的，指定了可接受的子协议。

### WebSocket 示例
1. WebSocket 服务连接[ Receive Message: {"code":200,"msg":"操作成功","data":"连接成功"} ];
2. 客户端发送消息[ Receive Message: {"code":200,"msg":"操作成功","data":{"userName":"WebSocket Test","msg":"o(ﾟДﾟ)っ啥！","userId":"10000"}} ];
3. 服务端向客户端发送消息[ Receive Message: {"code":200,"msg":"操作成功","data":"欢迎使用 WebSocket 服务！"} ];

![websocket](https://www.lmaye.com/group1/M00/00/00/CmiBTlwI0CSACT-2AABuwk5xwto669.png)

#### 参与贡献
1. 2018年12月5日: 初始化项目
2. 2018年12月5日: 初始化 spring-boot-websocket
3. 2018年12月6日: 实现websocket功能

#### 联系我
    * QQ: 379839355
    * QQ群: [Æ┊Java✍交流┊Æ](https://jq.qq.com/?_wv=1027&k=5Dqlg2L)
    * QQ群: [Cute Python](https://jq.qq.com/?_wv=1027&k=58hW2jl)
    * Email: lmay@lmaye.com
    * Home: [lmaye.com](https://www.lmaye.com)
    * GitHub: [lmayZhou](https://github.com/lmayZhou)
