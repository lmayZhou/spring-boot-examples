package com.lmaye.amqp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * -- 生产者端将消息发送出去，消息到达RabbitMQ之后，会返回一个到达确认。
 * 这个确认实际上就是官方常说的ConfirmCallback，我们通过在生产者端使用一个回调类来监听RabbiMQ返回的消息确认。
 * Spring AMQP中我们通过设置RabbitTemplate的ConfirmCallback属性来实现消息确认回调，通过一个实现了ConfirmCallback的类来实现回调逻辑。
 *
 * @author lmay.Zhou
 * @date 2021/5/14 12:01
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("MessageConfirm correlationData: {}, ack: {}, cause: {}", correlationData, ack, cause);
    }
}