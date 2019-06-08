package com.lmaye.spring.boot.configuration.metadata;

import com.lmaye.spring.boot.configuration.metadata.properties.BlogProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * -- 自定义配置元数据 Application
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019年6月9日 01:06:37
 */
@EnableConfigurationProperties({ BlogProperties.class })
@SpringBootApplication
public class SpringBootConfigurationMetadataApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigurationMetadataApplication.class, args);
    }
}
