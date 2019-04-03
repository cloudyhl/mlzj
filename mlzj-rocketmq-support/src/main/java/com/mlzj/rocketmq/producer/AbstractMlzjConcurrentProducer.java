package com.mlzj.rocketmq.producer;

import lombok.Data;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * @author yhl
 * @date 2019/3/25
 */
@Data
public abstract class MlzjAbstractConcurrentProducer {

    /**
     * 消息处理
     */
    protected DefaultMQProducer mqProducer = new DefaultMQProducer();

    /**
     * 主题
     */
    protected String topic;

    /**
     * 标签
     */
    protected String tags;

    /**
     * 发送消息
     * @param message 消息
     */
    public abstract void pushMessage(String message);

}
