package com.lmaye.amqp.controller;

import com.lmaye.amqp.config.RabbitConfirmCallback;
import com.lmaye.amqp.config.RabbitReturnCallback;
import com.lmaye.examples.common.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * --
 *
 * @author lmay.Zhou
 * @date 2021/5/14 14:11
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestSendController {
    /**
     * Rabbit Template
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        // 设置生产者消息确认
        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallback());
        rabbitTemplate.setReturnCallback(new RabbitReturnCallback());
    }

    @GetMapping("/send")
    public Response<Boolean> send() {
        String exchange = "exchange-rabbit-springboot-advance5";
        String routingKey = "product";
        String unRoutingKey = "norProduct";

        // 1.发送一条未被路由的消息, 触发 ReturnCallback、ConfirmCallback
        String message = LocalDateTime.now() + ", 发送一条消息.";
        rabbitTemplate.convertAndSend(exchange, unRoutingKey, message, new CorrelationData("unRouting-" + UUID.randomUUID()));
        log.info("发送一条消息,exchange: [{}], routingKey: [{}], message: [{}]", exchange, unRoutingKey, message);

        // 2.发送一条路由的消息, 触发ConfirmCallback
        rabbitTemplate.convertAndSend(exchange, routingKey, message, new CorrelationData("Routing-" + UUID.randomUUID()));
        log.info("发送一条消息,exchange: [{}], routingKey: [{}], message: [{}]", exchange, routingKey, message);

        // 3.直接向queue中发送测试, 供监听消费测试
        rabbitTemplate.convertAndSend("queue", "test queue message.");
        return Response.success(true);
    }

}
