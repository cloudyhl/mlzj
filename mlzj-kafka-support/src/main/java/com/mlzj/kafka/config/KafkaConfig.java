package com.mlzj.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/5/6
 */
@Configuration
public class KafkaConfig {

    @Resource
    private ConsumerFactory<Integer,String> consumerFactory;

    /**
     * 开启批量消费
     * @return 注入bean
     */
    @Bean("batchMessageFactory")
    public KafkaListenerContainerFactory<?> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(4);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.setBatchListener(true);
        //设置提交偏移量的方式
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }

    /**
     * 创建指定的topic 指定分区副本数
     * @return topic
     */
    @Bean
    public NewTopic mlzjKafka(){
        return new NewTopic("mlzj-topic",4,(short) 2);
    }

}
