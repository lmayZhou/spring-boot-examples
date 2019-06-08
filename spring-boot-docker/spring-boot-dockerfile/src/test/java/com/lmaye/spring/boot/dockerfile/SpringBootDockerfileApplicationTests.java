package com.lmaye.spring.boot.dockerfile;

import com.lmaye.spring.boot.dockerfile.properties.BlogProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDockerfileApplicationTests {
    @Autowired
    private BlogProperties blogProperties;

    /**
     * 应用配置自动注入
     */
    @Test
    public void contextLoads() {
        log.info("Host: [{}], Port: [{}], Email: [{}]", blogProperties.getHost(), blogProperties.getPort(),
                blogProperties.getEmail());
    }
}
