package com.lmaye.spring.boot.cache.guava.controller;

import com.lmaye.examples.common.common.Response;
import com.lmaye.spring.boot.cache.guava.annotation.CacheStorage;
import com.lmaye.spring.boot.cache.guava.entity.UserEntity;
import com.lmaye.spring.boot.cache.guava.param.QueryUserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * -- 用户 Controller
 *
 * @author lmay.Zhou
 * @date 2020/5/8 18:47 星期五
 * @email lmay_zlm@meten.com
 */
@Slf4j
@Api(value = "UserController", tags = "用户相关接口")
@RequestMapping("/user")
@RestController
public class UserController {
    /**
     * 查看用户
     *
     * @param param 请求参数
     * @return Mono<Response<UserEntity>>
     */
//    @CacheStorage(value = "User:", tags = {"id", "name", "email", "mobile"}, description = "缓存用户列表数据")
    @CacheStorage(value = "User:", description = "缓存用户列表数据")
    @PostMapping("/getUsers")
    @ApiOperation(value = "查看用户列表", notes = "查看用户列表的信息")
    public Response<List<UserEntity>> getUsers(@RequestBody @Valid QueryUserParam param) {
        log.info("<<<<<<<< 读取业务数据 >>>>>>>>");
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(new UserEntity(1, "T-1", "123456@qq.com", 123456L));
        userEntities.add(new UserEntity(2, "T-2", "123456@qq.com", 456789L));
        Integer id = param.getId();
        String email = param.getEmail();
        String name = param.getName();
        Long mobile = param.getMobile();
        if(!Objects.isNull(id)) {
            return Response.success(userEntities.stream().filter(it -> it.getId().equals(id)).collect(Collectors.toList()));
        }
        if(StringUtils.isNotBlank(email)) {
            return Response.success(userEntities.stream().filter(it -> it.getEmail().toLowerCase().contains(email.toLowerCase())).collect(Collectors.toList()));
        }
        if(StringUtils.isNotBlank(name)) {
            return Response.success(userEntities.stream().filter(it -> it.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList()));
        }
        if(!Objects.isNull(mobile)) {
            return Response.success(userEntities.stream().filter(it -> it.getMobile().equals(mobile)).collect(Collectors.toList()));
        }
        return Response.success(userEntities);
    }

    /**
     * Test
     *
     * @return Response<String>
     */
    @GetMapping("/test")
    @ApiOperation(value = "Test", notes = "Test")
    public Mono<Response<String>> test() {
        return Mono.just(Response.success("Test !"));
    }
}
