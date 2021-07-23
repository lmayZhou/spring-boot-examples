package com.lmaye.cloud.future.service.impl;

import com.lmaye.cloud.future.service.IFutureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * -- Future Service Impl
 *
 * @author lmay.Zhou
 * @date 2021/7/4 22:20
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@Service
public class FutureServiceImpl implements IFutureService {
    @Async
    @Override
    public void asyncTask() {
        try {
            String name = Thread.currentThread().getName();
            log.info("[asyncTask] 异步线程: [{}] >>> Start <<<", name);
            log.info("----------------- [asyncTask] 异步线程: [{}] 任务处理中 -----------------", name);
            Thread.sleep(10000);
            log.info("[asyncTask] 异步线程: [{}] >>> end <<<", name);
        } catch (Exception e) {
            log.error("处理异常: ", e);
        }
    }

    @Async
    @Override
    public Future<Integer> asyncTaskFuture1() {
        try {
            String name = Thread.currentThread().getName();
            log.info("[asyncTaskFuture1] 异步线程: [{}] >>> Start <<<", name);
            log.info("----------------- [asyncTaskFuture1] 异步线程: [{}] 任务处理中 -----------------", name);
            int x = 1 + 1;
            Thread.sleep(30000);
            log.info("[asyncTaskFuture1] 异步线程: [{}] >>> end [{}] <<<", name, x);
            return new AsyncResult<>(x);
        } catch (Exception e) {
            log.error("处理异常: ", e);
            return new AsyncResult<>(null);
        }
    }

    @Async
    @Override
    public Future<Integer> asyncTaskFuture2() {
        String name = Thread.currentThread().getName();
        log.info("[asyncTaskFuture2] 异步线程: [{}] >>> Start <<<", name);
        log.info("----------------- [asyncTaskFuture2] 异步线程: [{}] 任务处理中 -----------------", name);
        int y = 1 + 2;
        log.info("[asyncTaskFuture2] 异步线程: [{}] >>> end [{}] <<<", name, y);
        return new AsyncResult<>(y);
    }
}
