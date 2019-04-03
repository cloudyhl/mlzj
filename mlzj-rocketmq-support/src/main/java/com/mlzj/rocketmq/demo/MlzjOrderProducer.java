package com.mlzj.rocketmq.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * mq顺序发送
 * @author yhl
 * @date 2019/3/25
 */
@Slf4j
public class MlzjOrderProducer {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("WeiXin");
        defaultMQProducer.setNamesrvAddr("47.94.162.134:9876");
        defaultMQProducer.start();
        try {

            for (int i = 0; i < 100;i++ ){
                Message message = new Message("WeiXintest","test",("Hello"+i).getBytes());
                //发送消息时使用消息选择器,将需要顺序消费的消息发送到同一个队列,来保证消费时同一队列的消息按顺序消费
                SendResult send = defaultMQProducer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object args) {
                        Integer id = (Integer)args;
                        int finalId = id % list.size();
                        return list.get(finalId);
                    }
                },i);
                log.info("返回结果:{}",send);
            }
        } catch (Exception e) {
            log.error("send message fail");
        }
        defaultMQProducer.shutdown();
    }
}
