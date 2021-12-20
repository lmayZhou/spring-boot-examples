package com.lmaye.cloud.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * -- Example Delay Queue Application
 *
 * @author Lmay Zhou
 * @date 2021/12/14 11:42
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@EnableAsync
@SpringBootApplication
public class ExampleDelayQueueApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExampleDelayQueueApplication.class, args);
    }
}
