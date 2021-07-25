### 常见问题

1. ES-Head 连接Elasticsearch7.x 错误 406
```shell
# 1. 从docker容器把配置复制到宿主机
[root@lmaye ~]# docker cp es-head:/usr/src/app/_site ./vendor.js
# 2. "application/x-www-form-urlencoded" 修改为 "application/json;charset=UTF-8" (有2处修改)
[root@lmaye ~]# vi vendor.js
# 3. 最后把文件复制到docker容器中
[root@lmaye ~]# docker cp ./vendor.js es-head:/usr/src/app/_site
```

2. Kafka 查看 Topic 是否正常
```shell
# 进入kafka容器
[root@lmaye ~]# docker exec -it kafka bash
bash-5.1# cd /opt/kafka/bin/

# 创建topic
bash-5.1# cd ./kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic sync-logs-topic

# 查看topic列表
bash-5.1# ./kafka-topics.sh --list --zookeeper zookeeper:2181
__consumer_offsets
sync-logs-topic

# 生产消息
bash-5.1# ./kafka-console-producer.sh --broker-list kafka:9092 --topic sync-logs-topic
>hello

# 消费消息
bash-5.1# ./kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic sync-logs-topic --from-beginning
hello
```