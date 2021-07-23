package com.lmaye.spring.boot.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * -- WebSocket Application
 *
 * @author lmay.Zhou
 * @date 2018/12/6 14:06 星期四
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebsocketApplication.class, args);
    }
}
