package com.lmaye.cloud.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * -- Future Application
 *
 * @author lmay.Zhou
 * @date 2021/7/4 22:30
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@EnableAsync
@SpringBootApplication
public class FutureApplication {
    public static void main(String[] args) {
        SpringApplication.run(FutureApplication.class, args);
    }
}
