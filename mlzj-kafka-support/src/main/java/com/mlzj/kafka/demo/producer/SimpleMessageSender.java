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

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;


    public void sendMessage(){

    }
}
