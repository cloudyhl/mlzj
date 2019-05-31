package com.mlzj.kafka.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yhl
 * @date 2019/4/24
 */
@Component
public class SimpleMessageConsumer {


    @KafkaListener(topics = {"mlzj-topic"},concurrency = "4",containerFactory = "batchMessageFactory")
    public void listener(List<ConsumerRecord<String,String>> record, Acknowledgment acknowledgment){
        for (ConsumerRecord<String, String> consumerRecord : record){
            System.out.println(consumerRecord);
        }
        acknowledgment.acknowledge();
    }

}
