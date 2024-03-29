package com.lmaye.cloud.example.delayqueue.service.impl;

import com.lmaye.cloud.core.utils.GsonUtils;
import com.lmaye.cloud.example.delayqueue.DelayQueueProperties;
import com.lmaye.cloud.example.delayqueue.constant.QueueConstant;
import com.lmaye.cloud.example.delayqueue.entity.DelayQueueBody;
import com.lmaye.cloud.example.delayqueue.producer.KafkaProducer;
import com.lmaye.cloud.example.delayqueue.service.DelayQueueService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * -- Delay Queue Service
 *
 * @author Lmay Zhou
 * @date 2021/12/17 10:33
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@Service
public class DelayQueueServiceImpl implements DelayQueueService {
    /**
     * Redis Template
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Redisson Client
     */
    @Autowired
    private RedissonClient redissonClient;

    /**
     * Kafka Producer
     */
    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * Delay Queue Properties
     */
    @Autowired
    private DelayQueueProperties properties;

    /**
     * 任务处理
     */
    @Async
    @Override
    public void taskHandle() {
        Set<Object> dts = redisTemplate.opsForZSet().rangeByScore(properties.getQueueCacheKey(), 0, System.currentTimeMillis());
        assert dts != null;
        dts.forEach(it -> {
            log.debug("----- 任务开始，延迟队列Body: {}", it);
            DelayQueueBody body = GsonUtils.fromJson(it.toString(), DelayQueueBody.class);
            String serialNo = body.getSerialNo();
            RLock lock = redissonClient.getLock(properties.getLockKey() + serialNo);
            try {
                lock.tryLock(100, 300, TimeUnit.SECONDS);
                String key = QueueConstant.CACHE_KEY_CONSUME_FLAG + serialNo;
                if(Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
                    // 避免生产者重复推送消息
                    kafkaProducer.send(body.getTopic(), GsonUtils.toJson(body));
                    redisTemplate.opsForValue().set(key, false);
                }
                redisTemplate.opsForZSet().remove(properties.getQueueCacheKey(), it);
                log.debug("===== 任务处理完成 =====");
            } catch (Exception e) {
                log.error("处理失败: ", e);
            } finally {
                lock.unlock();
            }
        });
    }
}
