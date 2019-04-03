package com.mlzj.rocketmq.consumer;

import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * @author yhl
 * @date 2019/3/27
 */
@Data
public abstract class AbstractMlzjConsumer {

    /**
     * 消息消费器
     */
    protected DefaultMQPushConsumer mqPushConsumer = new DefaultMQPushConsumer();
    /**
     * 主题
     */
    protected String topic;

    /**
     * 标签
     */
    protected String tags;

}
