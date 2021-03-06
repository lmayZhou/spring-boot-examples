package com.lmaye.spring.boot.cache.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * -- Guava Cache Application
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020年5月8日 18:38:49
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootCacheGuavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheGuavaApplication.class, args);
    }
}
