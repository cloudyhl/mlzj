package com.mlzj.rocketmq.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MlzjRocketMqOrderlyProducer {
    /**
     * 初始队里数
     *
     * @return 队列数
     */
    int queueNumbers() default 4;

    /**
     * 主题名称
     *
     * @return 主题
     */
    String topic();

    /**
     * 标签
     *
     * @return 标签
     */
    String tags();

    /**
     * 发送消息延时
     *
     * @return 发送消息延时
     */
    int sendMsgTimeout() default 3000;

    /**
     * 压缩消息阙值
     *
     * @return 压缩消息阙值
     */
    int compressMsgBodyOverHowMuch() default 4096;

    /**
     * 在同步模式下声明发送失败之前要在内部执行的最大重试次数
     *
     * @return 最大重试次数
     */
    int retryTimesWhenSendFailed() default 2;


    /**
     * 在异步模式下声明发送失败之前要在内部执行的最大重试次数
     *
     * @return 最大重试次数
     */
    int retryTimesWhenSendAsyncFailed() default 2;

    /**
     * 消息失败后是否重试其他broker
     *
     * @return 是否重试其他broker
     */
    boolean retryAnotherBrokerWhenNotStoreOK() default false;

    /**
     * 允许最大的发送字节数
     *
     * @return 最大发送字节数
     */
    int maxMessageSize() default 4194304;

    /**
     * 传递的消息选择器类型
     *
     * @return 消息选择器类型
     */
    Class<?> messageQueueSelector();
}
