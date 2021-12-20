package com.lmaye.cloud.example.delayqueue;

import com.lmaye.cloud.core.utils.GsonUtils;
import com.lmaye.cloud.example.delayqueue.constant.QueueConstant;
import com.lmaye.cloud.example.delayqueue.entity.DelayQueueBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;
import java.util.UUID;

@SpringBootTest
class ExampleDelayQueueApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
        int x = 3;
        while(x > 0) {
            DelayQueueBody notice = DelayQueueBody.builder().serialNo(UUID.randomUUID().toString())
                    .topic(QueueConstant.TOPIC_DELAY_NOTICE).msg(String.format("%d分钟后通知", x)).build();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, x);
            redisTemplate.opsForZSet().add(QueueConstant.CACHE_KEY_DELAY_QUEUE, GsonUtils.toJson(notice), calendar.getTimeInMillis());

            DelayQueueBody invalidation = DelayQueueBody.builder().serialNo(UUID.randomUUID().toString())
                    .topic(QueueConstant.TOPIC_DELAY_INVALIDATION).msg(String.format("%d分钟后订单失效", x + 1)).build();
            calendar.add(Calendar.MINUTE, 1);
            redisTemplate.opsForZSet().add(QueueConstant.CACHE_KEY_DELAY_QUEUE, GsonUtils.toJson(invalidation), calendar.getTimeInMillis());
            --x;
        }
    }
}
