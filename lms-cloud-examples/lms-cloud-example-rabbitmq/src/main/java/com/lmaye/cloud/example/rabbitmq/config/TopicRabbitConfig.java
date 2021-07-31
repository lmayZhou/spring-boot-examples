package com.lmaye.cloud.example.rabbitmq.config;

import cn.hutool.core.util.StrUtil;
import com.lmaye.cloud.example.rabbitmq.constant.AppId;
import com.lmaye.cloud.example.rabbitmq.constant.TopicQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * -- Topic Rabbit Config
 *
 * @author Lmay Zhou
 * @date 2021/1/18 16:05
 * @email lmay@lmaye.com
 */
@Slf4j
@Configuration
public class TopicRabbitConfig {
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TopicQueue.TEST_TOPIC_EXCHANGE);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT, AppId.T1.getAppId()));
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT, AppId.T2.getAppId()));
    }

    /**
     * 匿名队列
     *
     * @return Queue
     */
    @Bean
    public Queue topicQueue3() {
        return new AnonymousQueue();
    }

    /**
     * 将Queue和Exchange绑定
     *
     * @return Binding
     */
    @Bean
    public Binding bindingExchangeMessage1() {
        return BindingBuilder.bind(topicQueue1()).to(exchange())
                .with(StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT, AppId.T1.getAppId()));
    }

    @Bean
    public Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(topicQueue2()).to(exchange())
                .with(StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT, AppId.T2.getAppId()));
    }

    @Bean
    public Binding bindingExchangeMessage3() {
        return BindingBuilder.bind(topicQueue3()).to(exchange()).with(TopicQueue.TEST_TOPIC_QUEUE);
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("MessageConfirm correlationData: {}, ack: {}, cause: {}", correlationData, ack, cause);
        });
        rabbitTemplate.setReturnsCallback((returnCallback) -> {
            log.info("message return message: {}, replyCode: {}, replyText: {}, exchange: {}, routingKey: {}",
                    returnCallback.getMessage(), returnCallback.getReplyCode(), returnCallback.getReplyText(),
                    returnCallback.getExchange(), returnCallback.getRoutingKey());
        });
        return rabbitTemplate;
    }
}
