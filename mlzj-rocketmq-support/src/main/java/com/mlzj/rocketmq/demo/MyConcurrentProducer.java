package com.mlzj.rocketmq.producer;

import com.mlzj.rocketmq.annotation.MlzjRocketMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/3/25
 * 测试注解
 */
@MlzjRocketMqProducer(topic = "WeiXintest",tags = "test")
@Slf4j
@Component
public class MyConcurrentProducer extends AbstractMlzjConcurrentProducer {
    @Override
    public void pushMessage(String message) {
        try {
            Message msg = new Message(this.topic,this.tags,message.getBytes());
            getMqProducer().send(msg);
        } catch (Exception e){
            log.error("发送消息失败:{}",e);
        }
    }
}
