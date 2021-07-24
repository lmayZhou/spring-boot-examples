package com.lmaye.spring.boot.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * -- ES搜索引擎 Application
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/11 23:47 星期二
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringBootElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticsearchApplication.class, args);
    }
}
