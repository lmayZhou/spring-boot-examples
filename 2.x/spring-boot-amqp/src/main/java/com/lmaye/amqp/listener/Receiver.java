package com.lmaye.amqp.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * --
 *
 * @author lmay.Zhou
 * @date 2021/5/14 12:01
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@Component
public class Receiver {
    @RabbitHandler
    @RabbitListener(queues = "queue-rabbit-springboot-advance5")
    public void process(String hello) {
        log.info("Receiver : {}", hello);
    }
}