package com.lmaye.amqp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * -- 设置 ReturnCallback 回调
 * 如果发送到交换器成功，但是没有匹配的队列，就会触发这个回调   在ConfirmCallback之前执行
 *
 * @author lmay.Zhou
 * @date 2021/5/14 12:01
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
public class RabbitReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("message return message: {}, replyCode: {}, replyText: {}, exchange: {}, routingKey: {}",
                message, replyCode, replyText, exchange, routingKey);
    }
}