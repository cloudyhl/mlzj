package com.mlzj.rocketmq.config;

import com.mlzj.rocketmq.annotation.*;
import com.mlzj.rocketmq.consumer.AbstractMlzjConsumer;
import com.mlzj.rocketmq.handler.MlzjRocketMqProducerInitHandler;
import com.mlzj.rocketmq.producer.AbstractMlzjConcurrentProducer;
import com.mlzj.rocketmq.producer.AbstractMlzjOrderlyProducer;
import com.mlzj.rocketmq.producer.AbstractMlzjTransactionProducer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author yhl
 * @date 2019/3/26
 */
@Configuration
@ConditionalOnBean(annotation = EnableMlzjRocketMq.class)
@EnableConfigurationProperties(MlzjRocketMqProperties.class)
public class MlzjRocketMqAutoConfiguration {

    @Resource
    private ApplicationContext applicationContext;

    private MlzjRocketMqProducerInitHandler mlzjRocketMqProducerInitHandler = new MlzjRocketMqProducerInitHandler();

    @Resource
    private MlzjRocketMqProperties mlzjRocketMqProperties;

    @PostConstruct
    private void init() throws MQClientException, IllegalAccessException, InstantiationException {
        Map<String, Object> mlzjProducers = applicationContext.getBeansWithAnnotation(MlzjRocketMqProducer.class);
        Map<String, Object> mlzjOrderlyProducers = applicationContext.getBeansWithAnnotation(MlzjRocketMqOrderlyProducer.class);
        Map<String, Object> mlzjTransactionProducers = applicationContext.getBeansWithAnnotation(MlzjRocketMqTransactionProducer.class);
        Map<String, Object> mlzjRocketMqConsumer = applicationContext.getBeansWithAnnotation(MlzjRocketMqConsumer.class);
        initCommonConsumer(mlzjRocketMqConsumer);
        initConcurrentProducer(mlzjProducers);
        initOrderlyProducer(mlzjOrderlyProducers);
        initTransactionProducer(mlzjTransactionProducers);

    }


    /**
     * 初始化producer
     *
     * @param mlzjRocketProducers producer集合
     */
    private void initConcurrentProducer(Map<String, Object> mlzjRocketProducers) throws MQClientException {
        for (Map.Entry<String, Object> producerEntry : mlzjRocketProducers.entrySet()) {
            Object producer = producerEntry.getValue();
            if (producer instanceof AbstractMlzjConcurrentProducer) {
                mlzjRocketMqProducerInitHandler.initConcurrentProducer((AbstractMlzjConcurrentProducer) producer, mlzjRocketMqProperties);
                ((AbstractMlzjConcurrentProducer) producer).getMqProducer().start();
            }
        }
    }

    /**
     * 初始化顺序消息发送
     *
     * @param mlzjOrderlyProducers 顺序消息发送者集合
     */
    private void initOrderlyProducer(Map<String, Object> mlzjOrderlyProducers) throws MQClientException, InstantiationException, IllegalAccessException {
        for (Map.Entry<String, Object> producerEntry : mlzjOrderlyProducers.entrySet()) {
            Object producer = producerEntry.getValue();
            if (producer instanceof AbstractMlzjOrderlyProducer) {
                mlzjRocketMqProducerInitHandler.initOrderlyProducer((AbstractMlzjOrderlyProducer) producer, mlzjRocketMqProperties);
                ((AbstractMlzjOrderlyProducer) producer).getMqProducer().start();
            }
        }
    }

    /**
     * 初始化事务消息
     *
     * @param mlzjTransactionProducers 事务消息发送者集合
     */
    private void initTransactionProducer(Map<String, Object> mlzjTransactionProducers) throws MQClientException {
        for (Map.Entry<String, Object> producerEntry : mlzjTransactionProducers.entrySet()) {
            Object producer = producerEntry.getValue();
            if (producer instanceof AbstractMlzjTransactionProducer){
                mlzjRocketMqProducerInitHandler.initTransactionProducer(
                    (AbstractMlzjTransactionProducer) producer,
                    mlzjRocketMqProperties,applicationContext);
                ((AbstractMlzjTransactionProducer) producer).getTransactionMQProducer().start();
            }
        }
    }

    /**
     * 初始化消费者
     * @param mlzjCommonConsumer 消费者
     */
    private void initCommonConsumer(Map<String, Object> mlzjCommonConsumer) throws MQClientException {
        for (Map.Entry<String, Object> consumerEntry : mlzjCommonConsumer.entrySet()){
            Object consumer = consumerEntry.getValue();
            if (consumer instanceof AbstractMlzjConsumer){
                mlzjRocketMqProducerInitHandler.initCommonConsumer(
                        (AbstractMlzjConsumer) consumer,
                        mlzjRocketMqProperties,applicationContext);
                ((AbstractMlzjConsumer) consumer).getMqPushConsumer().start();
            }
        }
    }
}
