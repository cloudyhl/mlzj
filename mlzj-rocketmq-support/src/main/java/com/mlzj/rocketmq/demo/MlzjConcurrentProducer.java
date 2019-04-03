package com.mlzj.rocketmq.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;



/**
 * rocketMq消息发送方
 * @author yhl
 * @date 2019/3/25
 */
@Slf4j
public class MlzjConcurrentProducer {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("WeiXin");
        defaultMQProducer.setNamesrvAddr("47.94.162.134:9876");
        defaultMQProducer.start();
        try {

            for (int i = 0; i < 10;i++ ){
                Message message = new Message("WeiXintest","test",("Hello"+i).getBytes());
                SendResult send = defaultMQProducer.send(message);
                System.out.println(send);
            }
        } catch (Exception e) {
            log.error("send message fail");
        }
        defaultMQProducer.shutdown();
    }

}
