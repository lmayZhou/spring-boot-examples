### 部署问题
1. master not discovered yet, this node has not previously joined a bootstrapped (v7+) cluster, and this node must discover master-eligible nodes [es-node1] to bootstrap a cluster: have discovered []; discovery will continue using [192.168.0.110:9300, 192.168.0.111:9300, 192.168.0.112:9300] from hosts providers and [{es-node2}{5XZDDaJCSVify-_Y01NFOw}{XQbK-jmhQ12XIPYh8UnZNA}{172.18.0.2}{172.18.0.2:9300}{ml.machine_memory=1927475200, xpack.installed=true, ml.max_open_jobs=20}] from last-known cluster state; node term 0, last-accepted version 0 in term 0
   1> 防火墙开放ES端口: 
```$bash
# 防火墙开启ES TCP端口
[root@es-node1 ~]# firewall-cmd --zone=public --add-port=9200/tcp --permanent
[root@es-node1 ~]# firewall-cmd --zone=public --add-port=9300/tcp --permanent
# spring boot application
[root@es-node1 ~]# firewall-cmd --zone=public --add-port=60/tcp --permanent
# logstash
[root@es-node1 ~]# firewall-cmd --zone=public --add-port=5044/tcp --permanent
# 重新载入
[root@es-node1 ~]# firewall-cmd --reload
```

   2> 发布主机IP, 修改ES配置文件(es-master.yml/es-slave1.yml/es-slave2.yml)并添加如下属性: 
```$bash
[root@es-node1 ~]# vi ./es-master.yml
# 配置发布主机IP
network.publish_host: 192.168.0.110
[root@es-node1 ~]# ./init.sh
[root@es-node1 ~]# docker logs -f es-master
...
{"type": "server", "timestamp": "2019-08-12T15:44:06,265+0000", "level": "INFO", "component": "o.e.t.TransportService", "cluster.name": "es-cluster", "node.name": "es-node1",  "message": "publish_address {192.168.0.110:9300}, bound_addresses {0.0.0.0:9300}"  }
...
``` 

2. 
