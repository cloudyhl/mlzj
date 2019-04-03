package com.mlzj.rocketmq.producer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author yhl
 * @date 2019/3/26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractMlzjOrderlyProducer extends BaseMlzjProducer{
    /**
     * 发送消息时队列的选择器
     */
    private MessageQueueSelector messageQueueSelector;

    /**
     * 发送顺序消息
     * @param message 消息
     * @param args 用于选择队列的参数
     */
    protected void sendSimpleOrderlyMessage(Message message, Object args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        this.mqProducer.send(message,this.messageQueueSelector,args);
    }
}
