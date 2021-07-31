package com.lmaye.cloud.example.rabbitmq.controller;

import cn.hutool.core.util.StrUtil;
import com.lmaye.cloud.example.rabbitmq.constant.AppId;
import com.lmaye.cloud.example.rabbitmq.constant.TopicQueue;
import com.lmaye.cloud.starter.rabbitmq.service.IRabbitmqService;
import com.lmaye.cloud.starter.web.context.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * -- Test Controller
 *
 * @author lmay.Zhou
 * @date 2021/5/14 14:11
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@AllArgsConstructor
@RestController
@RequestMapping("/test")
@Api(tags = "MQ消息推送相关接口")
public class TestController {
    /**
     * Rabbitmq Service
     */
    private final IRabbitmqService rabbitmqService;

    /**
     * 发送消息T1
     *
     * @param msg 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/send/t1")
    @ApiOperation("发送消息T1")
    public ResultVO<Boolean> sendT1(@RequestParam @ApiParam(value = "消息", required = true) String msg) {
        rabbitmqService.send(TopicQueue.TEST_TOPIC_EXCHANGE,
                StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT, AppId.T1.getAppId()), msg);
        return ResultVO.success(true);
    }

    /**
     * 发送消息T2
     *
     * @param msg 请求参数
     * @return ResultVO<Boolean>
     */
    @PostMapping("/send/t2")
    @ApiOperation("发送消息T2")
    public ResultVO<Boolean> sendT2(@RequestParam @ApiParam(value = "消息", required = true) String msg) {
        rabbitmqService.send(TopicQueue.TEST_TOPIC_EXCHANGE,
                StrUtil.format(TopicQueue.TEST_TOPIC_QUEUE_FMT, AppId.T2.getAppId()), msg);
        return ResultVO.success(true);
    }
}
