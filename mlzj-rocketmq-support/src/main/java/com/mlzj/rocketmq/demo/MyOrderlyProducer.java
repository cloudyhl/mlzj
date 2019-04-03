package com.mlzj.rocketmq.demo;

import com.mlzj.rocketmq.annotation.MlzjRocketMqOrderlyProducer;
import com.mlzj.rocketmq.producer.AbstractMlzjOrderlyProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/3/26
 */
@MlzjRocketMqOrderlyProducer(topic = "WeiXintest",tags = "test",messageQueueSelector = DemoOrderLySelector.class,queueNumbers = 10)
@Component
@Slf4j
public class MyOrderlyProducer extends AbstractMlzjOrderlyProducer {

    public void pushMessage(String message){
        for (int i = 0 ; i < 10 ; i++){
            Message simpleMessage = createSimpleMessage(message + i);
            try {
                this.sendSimpleOrderlyMessage(simpleMessage,i);
            } catch (Exception e) {
                log.error("send orderly message error",e);
            }
        }
    }
}
