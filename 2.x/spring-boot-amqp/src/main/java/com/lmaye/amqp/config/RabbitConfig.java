package com.lmaye.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * --
 *
 * @author lmay.Zhou
 * @date 2021/5/14 12:01
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Configuration
public class RabbitConfig {
    // 实例Id 从阿里云AMQP控制台获取
    /*private static final String INSTANCE_ID = "amqp-cn-xxx";

    @Autowired
    private RabbitProperties rabbitProperties;

    @Bean
    public ConnectionFactory getConnectionFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory =
                new com.rabbitmq.client.ConnectionFactory();
        rabbitConnectionFactory.setHost(rabbitProperties.getHost());
        rabbitConnectionFactory.setPort(rabbitProperties.getPort());
        rabbitConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());

        AliyunCredentialsProvider credentialsProvider = new AliyunCredentialsProvider(
                rabbitProperties.getUsername(), rabbitProperties.getPassword(), INSTANCE_ID);
        rabbitConnectionFactory.setCredentialsProvider(credentialsProvider);
        rabbitConnectionFactory.setAutomaticRecoveryEnabled(true);
        rabbitConnectionFactory.setNetworkRecoveryInterval(5000);
        ConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);
        ((CachingConnectionFactory) connectionFactory).setPublisherConfirms(rabbitProperties.isPublisherConfirms());
        ((CachingConnectionFactory) connectionFactory).setPublisherReturns(rabbitProperties.isPublisherReturns());
        return connectionFactory;
    }*/

    @Bean
    public Queue queue() {
        Map<String, Object> arguments = new HashMap<>(4);
        return new Queue("queue-rabbit-springboot-advance5", true, false, false, arguments);
    }

    @Bean
    public Exchange exchange() {
        Map<String, Object> arguments = new HashMap<>(4);
        return new DirectExchange("exchange-rabbit-springboot-advance5", true, false, arguments);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("product").noargs();
    }
}