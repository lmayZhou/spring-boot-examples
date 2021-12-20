package com.lmaye.cloud.example.delayqueue.constant;

/**
 * -- Queue Constant
 *
 * @author Lmay Zhou
 * @date 2021/12/17 10:03
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
public interface QueueConstant {
    /**
     * KEY - 分布式锁
     */
    String CACHE_KEY_LOCK = "DistributedKey:";

    /**
     * KEY - 延迟队列
     */
    String CACHE_KEY_DELAY_QUEUE = "DelayQueue";

    /**
     * KEY - 消费标记
     */
    String CACHE_KEY_CONSUME_FLAG = "ConsumeFlag:";

    /**
     * Topic - 消息通知
     */
    String TOPIC_DELAY_NOTICE = "topic.msg.delay.notice";

    /**
     * Topic - 任务失效
     */
    String TOPIC_DELAY_INVALIDATION = "topic.msg.delay.invalidation";
}
