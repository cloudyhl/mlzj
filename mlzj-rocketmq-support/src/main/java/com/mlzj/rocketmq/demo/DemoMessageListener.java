package com.mlzj.rocketmq.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yhl
 * @date 2019/3/27
 */
@Component
@Slf4j
public class DemoMessageListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt messageExt : msgs) {
            System.out.println(messageExt.getQueueId()+new String(messageExt.getBody()));
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                log.error("system.out.println error from this");
            }

        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
