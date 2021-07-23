package com.lmaye.spring.boot.configuration.metadata;

import com.lmaye.spring.boot.configuration.metadata.properties.BlogProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 应用配置自动元数据
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigurationMetadataApplicationTests {
    @Autowired
    private BlogProperties blogProperties;

    /**
     * 测试方法
     */
    @Test
    public void contextLoads() {
        log.info("Host: [{}], Port: [{}], Email: [{}]", blogProperties.getHost(), blogProperties.getPort(),
                blogProperties.getEmail());
    }
}
