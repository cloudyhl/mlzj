package com.mlzj.kafka.demo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/4/24
 */
@Component
public class SimpleMessageSender {

    private String topic = "mlzj-topic";

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    public void sendMessage(String message){
        kafkaTemplate.send(topic,message);
    }
}
