package com.lmaye.spring.boot.dockerfile.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -- DockerController
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/5/30 23:37 星期四
 */
@RestController
public class DockerController {
    /**
     * 测试方法
     *
     * @return String
     */
    @GetMapping("/")
    public String index() {
        return "Hello Docker !";
    }
}
