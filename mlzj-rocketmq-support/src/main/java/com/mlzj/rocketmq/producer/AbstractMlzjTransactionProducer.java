package com.mlzj.rocketmq.producer;

import lombok.Data;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

/**
 * @author yhl
 * @date 2019/3/27
 */
@Data
public class AbstractMlzjTransactionProducer {
    /**
     * 事务消息发送器
     */
    protected TransactionMQProducer transactionMQProducer = new TransactionMQProducer();
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
