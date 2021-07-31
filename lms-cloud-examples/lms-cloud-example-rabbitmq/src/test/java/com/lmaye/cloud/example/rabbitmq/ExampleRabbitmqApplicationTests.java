package com.lmaye.cloud.example.rabbitmq;

import cn.hutool.core.util.StrUtil;
import com.lmaye.cloud.example.rabbitmq.constant.AppId;
import com.lmaye.cloud.example.rabbitmq.constant.TopicQueue;
import com.lmaye.cloud.starter.rabbitmq.service.IRabbitmqService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExampleRabbitmqApplicationTests {
    /**
     * Rabbitmq Service
     */
    @Autowired
    private IRabbitmqService rabbitmqService;

    @Test
    void contextLoads() {
        rabbitmqService.send(TopicQueue.TEST_TOPIC_EXCHANGE, StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT,
                AppId.T1.getAppId()), AppId.T1.getAppId() + ": 消息测试");

        rabbitmqService.send(TopicQueue.TEST_TOPIC_EXCHANGE, StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT,
                AppId.T2.getAppId()), AppId.T2.getAppId() + ": 消息测试");
    }
}
