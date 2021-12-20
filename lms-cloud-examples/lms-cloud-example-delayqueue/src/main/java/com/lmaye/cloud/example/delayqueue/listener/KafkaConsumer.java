package com.lmaye.cloud.example.delayqueue.listener;

import com.lmaye.cloud.core.utils.GsonUtils;
import com.lmaye.cloud.example.delayqueue.constant.QueueConstant;
import com.lmaye.cloud.example.delayqueue.entity.DelayQueueBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * -- Kafka Consumer
 *
 * @author Lmay Zhou
 * @date 2021/12/16 13:54
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Slf4j
@Component
public class KafkaConsumer {
    /**
     * Redis Template
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 消费
     *
     * @param record 消息记录
     * @param ack    消息确认
     * @throws InterruptedException 异常
     */
    @KafkaListener(topics = QueueConstant.TOPIC_DELAY_NOTICE, groupId = "delay-queue-001")
    public void consumerNotice(ConsumerRecord<String, String> record, Acknowledgment ack) throws InterruptedException {
        DelayQueueBody body = GsonUtils.fromJson(record.value(), DelayQueueBody.class);
        String key = QueueConstant.CACHE_KEY_CONSUME_FLAG + body.getSerialNo();
        Boolean flag = (Boolean) redisTemplate.opsForValue().get(key);
        if(Boolean.TRUE.equals(flag)) {
            // 避免消费者重复消费
            ack.acknowledge();
            return;
        }
        log.info("【{}】{} 接收到kafka消息: {}", record.topic(), record.offset(), record.value());
        TimeUnit.SECONDS.sleep(1);
        ack.acknowledge();
        redisTemplate.opsForValue().set(key, true);
    }

    /**
     * 消费
     *
     * @param record 消息记录
     * @param ack    消息确认
     * @throws InterruptedException 异常
     */
    @KafkaListener(topics = QueueConstant.TOPIC_DELAY_INVALIDATION, groupId = "delay-queue-001")
    public void consumerInvalidation(ConsumerRecord<String, String> record, Acknowledgment ack) throws InterruptedException {
        log.info("【{}】{} 接收到kafka消息: {}", record.topic(), record.offset(), record.value());
        TimeUnit.SECONDS.sleep(1);
        ack.acknowledge();
    }
}
