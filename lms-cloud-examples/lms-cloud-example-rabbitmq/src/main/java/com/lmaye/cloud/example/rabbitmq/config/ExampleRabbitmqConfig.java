package com.lmaye.cloud.example.rabbitmq.config;

import com.lmaye.cloud.starter.rabbitmq.service.impl.RabbitmqServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * -- Example Rabbitmq Config
 *
 * @author Lmay Zhou
 * @date 2021/7/31 11:54
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Import({RabbitmqServiceImpl.class})
@Configuration
public class ExampleRabbitmqConfig {
}
