### 一、介绍
&emsp;&emsp;WebRTC 是Google推出的基于浏览器的实时语音-视频通讯架构。其典型的应用场景为：浏览器之间端到端(p2p)实时视频对话，但由于网络环境的复杂性(比如：路由器/交换机/防火墙等），
浏览器与浏览器很多时候无法建立p2p连接，只能通过公网上的中继服务器(也就是所谓的turn服务器)中转。

&emsp;&emsp;接下来将通过在Linux服务器上安装Coturn这个软件，实现搭建STUN服务器和TURN服务器。
Coturn 是一个免费的开源的 TURN/STUN 服务器。Coturn 服务器完整的实现了 STUN/TURN/ICE 协议，支持 P2P 穿透防火墙。
STUN 服务器用于检测NAT类型。
TURN 服务器是在点对点失败后用于通信中继。

### 二、服务安装相关
#### 1. 相关依赖
```shell script
[root@localhost ~]# yum install -y make gcc cc gcc-c++ wget openssl-devel libevent libevent-devel
```

#### 2. 下载源码包
[Coturn Github](https://github.com/coturn/coturn/wiki/Downloads)
```shell script
# 1. 下载
[root@localhost download]# wget https://coturn.net/turnserver/v4.5.0.8/turnserver-4.5.0.8.tar.gz
# 2. 解压
[root@localhost download]# tar -zxvf turnserver-4.5.0.8.tar.gz
# 3. 进入目录
[root@localhost download]# cd turnserver-4.5.0.8/
```

#### 3. 编译安装
```shell script
# 1. 指定安装目录
[root@localhost turnserver-4.5.0.8]# ./configure --prefix=/usr/local/turnserver
# 2. 安装
[root@localhost turnserver-4.5.0.8]# make && make install
```

#### 4. 设置环境变量
```shell script
# 1. 编辑root目录下的.bashrc文件
[root@localhost ~]# vi ~/.bashrc
# 2. 写入内容
export turnserver_home=/usr/local/turnserver
export PATH=$PATH:$turnserver_home/bin
```

#### 5. 编辑配置文件
```shell script
# 1. 查找配置文件目录
[root@localhost ~]# find /usr -name turnserver.conf
/usr/local/turnserver/share/examples/turnserver/etc/turnserver.conf
# 2. 编辑配置文件
[root@localhost ~]# vi /usr/local/turnserver/share/examples/turnserver/etc/turnserver.conf
# 监听端口可以不设置会默认的使用3478
listening-port=3478
# listening-ip,注意必须是你的内网IP地址
listening-ip=192.168.0.8
# external-ip，注意必须使用你的外网IP地址
external-ip=192.168.0.8
# 设置用户名及密码，这个是作为TURN服务器使用必须设置的,可以设置多个
user=user:123456  
```

#### 6. 启动/关闭服务
```shell script
# 启动服务
[root@localhost ~]# turnserver -v -r 外网ip -a -o -c /usr/local/turnserver/share/examples/turnserver/etc/turnserver.conf
# 关闭服务
[root@localhost ~]# cat /var/run/turnserver.pid 
4603
[root@localhost ~]# kill -9 4603
```

### 三、测试服务
```shell script
# 测试 STUN (使用下面的命令即可测试 STUN 服务使用可用，唯一此参数是 STUN 服务器的 IP地址或域名。)
[root@localhost ~]# turnutils_stunclient 172.31.6.105
0: IPv4. UDP reflexive addr: 172.31.6.105:38377
# 测试 TURN (使用下面的命令即可测试 TURN 服务使用可用，值得注意的是必须使用 turnserver 启动时指定 Realm 下的用户。)
[root@localhost ~]# turnutils_uclient -u user -w 123456 172.31.6.105
0: Total connect time is 0
1: start_mclient: msz=2, tot_send_msgs=0, tot_recv_msgs=0, tot_send_bytes ~ 0, tot_recv_bytes ~ 0
2: start_mclient: msz=2, tot_send_msgs=0, tot_recv_msgs=0, tot_send_bytes ~ 0, tot_recv_bytes ~ 0
3: start_mclient: msz=2, tot_send_msgs=0, tot_recv_msgs=0, tot_send_bytes ~ 0, tot_recv_bytes ~ 0
4: start_mclient: msz=2, tot_send_msgs=0, tot_recv_msgs=0, tot_send_bytes ~ 0, tot_recv_bytes ~ 0
5: start_mclient: msz=2, tot_send_msgs=5, tot_recv_msgs=0, tot_send_bytes ~ 500, tot_recv_bytes ~ 0
...
15: start_mclient: msz=1, tot_send_msgs=10, tot_recv_msgs=0, tot_send_bytes ~ 1000, tot_recv_bytes ~ 0
15: start_mclient: tot_send_msgs=10, tot_recv_msgs=0
15: start_mclient: tot_send_bytes ~ 1000, tot_recv_bytes ~ 0
15: Total transmit time is 15
15: Total lost packets 10 (100.000000%), total send dropped 0 (0.000000%)
15: Average round trip delay 0.000000 ms; min = 4294967295 ms, max = 0 ms
15: Average jitter -nan ms; min = 4294967295 ms, max = 0 ms
```

[STUN/TURN 外网ice测试](https://webrtc.github.io/samples/src/content/peerconnection/trickle-ice/)
输入服务信息，然后进行服务测试:
STUN or TURN URI: turn:公网IP:3478
TURN username: user
TURN password: 123456