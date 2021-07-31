package com.lmaye.cloud.example.rabbitmq.constant;

/**
 * -- Queue Constant
 *
 * @author Lmay Zhou
 * @date 2021/7/31 11:11
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
public interface TopicQueue {
    /**
     * 主题交换机
     */
    String TEST_TOPIC_EXCHANGE = "topic.exchange.test";

    /**
     * 消息队列(通配符模式)
     * - 匹配数量
     * - *: 1
     * - #: 0~n
     */
    String TEST_TOPIC_QUEUE = "topic.queue.test.*";

    /**
     * 消息队列(格式化)
     */
    String TEST_TOPIC_QUEUE_FMT = "topic.queue.test.{}";
}
