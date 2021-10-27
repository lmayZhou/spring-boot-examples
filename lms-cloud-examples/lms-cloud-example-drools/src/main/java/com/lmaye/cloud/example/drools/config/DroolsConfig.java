package com.lmaye.cloud.example.drools.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * -- Drools Config
 *
 * @author Lmay Zhou
 * @date 2021/10/21 16:53
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Configuration
@MapperScan("com.lmaye.cloud.example.drools.mapper")
public class DroolsConfig {
}
