package com.lmaye.spring.boot.cache.guava.aspect;

import com.google.common.cache.Cache;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lmaye.examples.common.common.Response;
import com.lmaye.examples.common.utils.GsonUtils;
import com.lmaye.spring.boot.cache.guava.annotation.CacheStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * -- 缓存AOP切面
 *
 * @author lmay.Zhou
 * @date 2020/5/9 10:26 星期六
 * @email lmay_zlm@meten.com
 */
@Slf4j
@Aspect
@Component
public class CacheAspect {
    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.lmaye.spring.boot.cache.guava.annotation.CacheStorage)")
    public void cachePoint() {

    }

    /**
     * 环绕通知
     *
     * @param joinPoint join point
     * @throws Throwable Throwable
     */
    @Around("cachePoint()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        log.info("方法: {}", signature.getName());
        log.info("入参: {}", GsonUtils.toJson(args));
        // 缓存注解
        String cacheKey = getCacheKey(signature, args[0]);
        // 获取缓存
        Cache<String, Object> cache = GuavaCacheUtil.getInstance();
        Object data = cache.getIfPresent(cacheKey);
        if (!Objects.isNull(data)) {
            log.info(">>>>>>>> 读取缓存: [{}] <<<<<<<<", cacheKey);
            return Response.success(data);
        }
        return joinPoint.proceed();
    }

    /**
     * 后置通知
     *
     * @param rs result
     * @throws Throwable Throwable
     */
    @AfterReturning(returning = "rs", pointcut = "cachePoint()")
    public void doAfter(JoinPoint joinPoint, Object rs) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        log.info("方法: {}", signature.getName());
        log.info("响应: {}", GsonUtils.toJson(rs));
        String cacheKey = getCacheKey(signature, args[0]);
        // 通过CacheBuilder构建一个缓存实例
        Cache<String, Object> cache = GuavaCacheUtil.getInstance();
        Object cacheData = cache.getIfPresent(cacheKey);
        if (Objects.isNull(cacheData)) {
            // 缓存数据
            log.info(">>>>>>>> 缓存数据: [{}] <<<<<<<<", cacheKey);
            cache.put(cacheKey, GsonUtils.fromJson(GsonUtils.toJson(rs), Response.class).getData());
        }
    }

    /**
     * 获取缓存Key
     *
     * @param signature signature
     * @param arg       参数
     * @return String
     */
    private String getCacheKey(Signature signature, Object arg) {
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 缓存注解
        CacheStorage cacheStorage = method.getAnnotation(CacheStorage.class);
        String[] tags = cacheStorage.tags();
        StringBuilder cacheKeyBuilder = new StringBuilder(cacheStorage.value());
        JsonObject json = GsonUtils.fromJson(GsonUtils.toJson(arg), JsonObject.class);
        for (String tag : tags) {
            JsonElement jsonElement = json.has(tag) ? json.get(tag) : null;
            if (!Objects.isNull(jsonElement) && StringUtils.isNotBlank(jsonElement.getAsString())) {
                cacheKeyBuilder.append(jsonElement.getAsString());
            } else {
                cacheKeyBuilder.append(tag);
            }
            cacheKeyBuilder.append("&");
        }
        return cacheKeyBuilder.substring(0, cacheKeyBuilder.lastIndexOf("&"));
    }
}
