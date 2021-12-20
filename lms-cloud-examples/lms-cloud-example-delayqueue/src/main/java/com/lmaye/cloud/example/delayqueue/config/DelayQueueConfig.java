package com.lmaye.cloud.example.delayqueue.config;

import cn.hutool.core.thread.NamedThreadFactory;
import com.lmaye.cloud.example.delayqueue.service.DelayQueueService;
import com.lmaye.cloud.example.delayqueue.task.RedisDelayQueueTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.*;

/**
 * -- Delay Queue Config
 *
 * @author Lmay Zhou
 * @date 2021/12/15 11:55
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Configuration
@EnableCaching
public class DelayQueueConfig {
    /**
     * Delay Queue Service
     */
    @Autowired
    private DelayQueueService delayQueueService;

    /**
     * 配置自定义 RedisTemplate
     *
     * @param redisConnectionFactory RedisConnectionFactory
     * @return RedisTemplate<String, Object>
     */
    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 使用StringRedisSerializer来序列化和反序列化redis的key
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 执行服务
     *
     * @return ExecutorService
     */
    @Bean(destroyMethod = "shutdown")
    ExecutorService executorService() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10,
                new NamedThreadFactory("scheduleThreadPool", false), new ThreadPoolExecutor.AbortPolicy());
        executorService.scheduleWithFixedDelay(new RedisDelayQueueTask(delayQueueService), 0, 30, TimeUnit.SECONDS);
        return executorService;
    }
}
