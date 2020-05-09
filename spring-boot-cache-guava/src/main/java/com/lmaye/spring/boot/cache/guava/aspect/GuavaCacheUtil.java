package com.lmaye.spring.boot.cache.guava.aspect;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * -- Guava 本地高效缓存
 *
 * @author lmay.Zhou
 * @date 2020/5/9 14:46 星期六
 * @email lmay_zlm@meten.com
 */
public class GuavaCacheUtil {
    /**
     * 静态类
     */
    private static class CacheHandler {
        private final static Cache<String, Object> CACHE = CacheBuilder.newBuilder()
            // 设置缓存的最大容量
            .maximumSize(100)
            // 缓存被访问后,一定时间后失效
            .expireAfterAccess(30, TimeUnit.MINUTES)
            // 设置并发级别为10
            .concurrencyLevel(10)
            // 开启缓存统计
            .recordStats()
            .build();
    }

    private GuavaCacheUtil() {
    }

    public static Cache<String, Object> getInstance() {
        return CacheHandler.CACHE;
    }
}
