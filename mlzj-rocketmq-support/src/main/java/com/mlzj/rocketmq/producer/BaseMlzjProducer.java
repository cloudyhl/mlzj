package com.mlzj.rocketmq.producer;

import lombok.Data;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author yhl
 * @date 2019/3/26
 */
@Data
public class BaseMlzjProducer {
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
     * 创建简单消息
     * @param message 具体消息
     * @return mq消息
     */
    protected Message createSimpleMessage(String message){
        return new Message(this.topic,this.tags,message.getBytes());
    }
}
