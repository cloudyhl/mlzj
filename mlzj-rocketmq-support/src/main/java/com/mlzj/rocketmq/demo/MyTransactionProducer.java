package com.mlzj.rocketmq.demo;

import com.mlzj.rocketmq.annotation.MlzjRocketMqTransactionProducer;
import com.mlzj.rocketmq.producer.AbstractMlzjTransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/3/27
 */
@MlzjRocketMqTransactionProducer(topic = "WeiXintest",tags = "test",transactionalListener = MlzjTransactionListener.class)
@Component
@Slf4j
public class MyTransactionProducer extends AbstractMlzjTransactionProducer {

    /**
     * 发送事务消息
     * @param message 发送事务消息
     */
    public void pushMessage(String message,Object o) {
        Message simpleMessage = this.createSimpleMessage(message);
        try {
            this.transactionMQProducer.sendMessageInTransaction(simpleMessage,o);
        } catch (MQClientException e) {
            log.error("push message in transactional failed",e);
        }
    }
}
