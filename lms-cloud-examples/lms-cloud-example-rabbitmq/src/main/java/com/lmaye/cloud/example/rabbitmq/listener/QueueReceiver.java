package com.lmaye.cloud.example.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * -- Queue Receiver
 *
 * @author lmay.Zhou
 * @date 2021/5/14 12:01
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@Component
public class QueueReceiver {
    /**
     * TopicQueue1
     *
     * @param msg 消息
     */
    @RabbitHandler
    @RabbitListener(queues = "#{topicQueue1.name}")
    public void process1(String msg) {
        log.info("TopicQueue1 Receiver : {}", msg);
    }

    /**
     * TopicQueue2
     *
     * @param msg 消息
     */
    @RabbitHandler
    @RabbitListener(queues = "#{topicQueue2.name}")
    public void process2(String msg) {
        log.info("TopicQueue2 Receiver : {}", msg);
    }
}