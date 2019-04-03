package com.mlzj.rocketmq.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息消费
 * @author yhl
 * @date 2019/3/25
 */
public class MlzjConcurrentConsumer {

    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("WeiXin");
        consumer.setNamesrvAddr("47.94.162.134:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        Map<String,String> subscription = new HashMap<>(4);
        subscription.put("WeiXintest","*");
        consumer.setSubscription(subscription);
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : list) {
                System.out.println(messageExt.getQueueId()+new String(messageExt.getBody()));
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }

            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("start");
    }
}
