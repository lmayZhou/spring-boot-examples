package com.lmaye.cloud.future.controller;

import com.lmaye.cloud.future.service.IFutureService;
import com.lmaye.examples.common.common.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * -- Future Controller
 *
 * @author lmay.Zhou
 * @date 2021/7/4 22:30
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@AllArgsConstructor
@RestController
@RequestMapping("/future")
public class FutureController {
    /**
     * Future Service
     */
    private final IFutureService futureService;

    @GetMapping("/asyncTask")
    public Response<String> asyncTask() {
        futureService.asyncTask();
        return Response.success("OK");
    }

    @GetMapping("/asyncTaskFuture")
    public Response<Integer> asyncTaskFuture() throws Exception {
        // 执行异步任务
        Future<Integer> x = futureService.asyncTaskFuture1();
        Future<Integer> y = futureService.asyncTaskFuture2();
        // 获取结果
        return Response.success(x.get(60, TimeUnit.SECONDS) + y.get());
    }
}
