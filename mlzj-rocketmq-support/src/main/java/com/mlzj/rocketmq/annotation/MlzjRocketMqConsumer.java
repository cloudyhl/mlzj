package com.mlzj.rocketmq.annotation;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.lang.annotation.*;

/**
 * @author yhl
 * @date 2019/3/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MlzjRocketMqConsumer {


    String[] subscription();

    /**
     * 从什么位置开始消费
     *
     * @return 从什么位置消费
     */
    ConsumeFromWhere consumeFromWhere() default ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

    /**
     * 消息监听器
     *
     * @return 消息监听器
     */
    Class<? extends MessageListener> messageListener();

    /**
     * 最小线程数
     *
     * @return 线程数
     */
    int consumeThreadMin() default 20;

    /**
     * 最大线程数
     *
     * @return 线程数
     */
    int consumeThreadMax() default 64;

    /**
     * 线程池动态调整阙值
     *
     * @return 阙值
     */
    int adjustThreadPoolNumsThreshold() default 100000;

    /**
     * 同时最大跨距偏移。它对连续消耗没有影响。
     *
     * @return 偏移
     */
    int consumeConcurrentlyMaxSpan() default 2000;

    /**
     * 队列级别的流控制阈值，默认情况下每个消息队列最多缓存1000条消息 瞬时值可能超过限制。
     *
     * @return 可超过阙值
     */
    int pullThresholdForQueue() default 1000;

    /**
     * *限制队列级别的缓存消息大小，每个消息队列默认最多缓存100 mib消息，
     *
     * @return 缓存大小
     */
    int pullThresholdSizeForQueue() default 100;

    /**
     * 主题级别的流控制阈值，默认值为-1（无限制）
     *
     * @return 默认值为-1
     */
    int pullThresholdForTopic() default -1;

    /**
     * 限制主题级别的缓大小，默认值为-1 mib（无限制)
     *
     * @return 限制
     */
    int pullThresholdSizeForTopic() default -1;


    /**
     * 消息提取时间间隔
     *
     * @return 时间间隔
     */
    int pullInterval() default 0;

    /**
     * 每次拉取消息数默认1
     *
     * @return 每次拉取消息数
     */
    int consumeMessageBatchMaxSize() default 1;

    /**
     * 拉取大小
     *
     * @return 拉取大小
     */
    int pullBatchSize() default 32;

    /**
     * 每次拉取时是否更新订阅关系
     *
     * @return 是否更新订阅关系
     */
    boolean postSubscriptionWhenPull() default false;

    /**
     * Whether the unit of subscription group
     *
     * @return 订阅组
     */
    boolean unitMode() default false;


    /**
     * 最大消耗时间 -1  标识16
     *
     * @return 最大消费次数
     */
    int maxReconsumeTimes() default -1;

    /**
     * 对于需要缓慢牵引的情况，如流量控制场景，暂停牵引时间。
     *
     * @return 暂停牵引时间
     */
    long suspendCurrentQueueTimeMillis() default 1000;

    /**
     * 消息可能阻塞消耗线程的最长时间（分钟）。
     *
     * @return 阻塞分钟数
     */
    long consumeTimeout() default 15;

    /**
     * 消息模式 集群，广播
     * @return 消费模式
     */
    MessageModel messageModel() default MessageModel.CLUSTERING;
}
