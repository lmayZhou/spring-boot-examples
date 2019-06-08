package com.lmaye.spring.boot.configuration.metadata.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * -- 测试属性配置类
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/7 11:27 星期五
 */
@Data
@Component
@ConfigurationProperties("blog")
public class BlogProperties {
    /**
     * Host
     */
    private String host;

    /**
     * Port
     */
    private Integer port;

    /**
     * Email
     */
    private String email;
}
