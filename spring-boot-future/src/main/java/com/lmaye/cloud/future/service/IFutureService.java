package com.lmaye.cloud.future.service;

import java.util.concurrent.Future;

/**
 * -- Future Service
 *
 * @author lmay.Zhou
 * @date 2021/7/4 22:16
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
public interface IFutureService {
    /**
     * 无返回参数方法
     */
    void asyncTask();

    /**
     * 有返回参数方法
     *
     * @return Future<Integer>
     */
    Future<Integer> asyncTaskFuture1();

    /**
     * 有返回参数方法
     *
     * @return Future<Integer>
     */
    Future<Integer> asyncTaskFuture2();
}
