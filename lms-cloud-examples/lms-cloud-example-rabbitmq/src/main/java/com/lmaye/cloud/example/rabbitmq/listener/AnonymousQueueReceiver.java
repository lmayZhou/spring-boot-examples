package com.lmaye.cloud.example.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * -- Anonymous Queue Receiver
 * - 通配模式
 *
 * @author Lmay Zhou
 * @date 2021/7/31 22:38
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@Component
@RabbitListener(queues = "#{topicQueue3.name}")
public class AnonymousQueueReceiver {
    /**
     * AnonymousQueue
     *
     * @param msg 消息
     */
    @RabbitHandler
    public void process(String msg) {
        log.info("AnonymousQueue Receiver : {}", msg);
    }
}
