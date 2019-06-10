package com.lmaye.spring.boot.dockerfile.controller;

import com.lmaye.examples.common.common.Response;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return Response
     */
    @PostMapping("/login")
    public Response login(@RequestParam String username, @RequestParam String password) throws InterruptedException {
        Thread.sleep(70 * 1000);
        if(!Objects.equals("root", username) || !Objects.equals("root", password)) {
            return Response.failed("用户名或密码错误！");
        }
        Map<String, Object> result = new HashMap<>(1);
        result.put("token", username + ":" + password);
        return Response.success(result);
    }

    /**
     * 测试方法
     *
     * @param token token
     * @return Response
     */
    @GetMapping("/test/{token}")
    public Response test(@PathVariable String token) {
        if(!Objects.equals("root:root", token)) {
            return Response.failed("无效Token！");
        }
        return Response.success(token);
    }
}
