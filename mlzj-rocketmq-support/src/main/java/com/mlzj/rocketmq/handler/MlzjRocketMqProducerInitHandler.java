package com.mlzj.rocketmq.handler;

import com.mlzj.rocketmq.annotation.MlzjRocketMqConsumer;
import com.mlzj.rocketmq.annotation.MlzjRocketMqOrderlyProducer;
import com.mlzj.rocketmq.annotation.MlzjRocketMqProducer;
import com.mlzj.rocketmq.annotation.MlzjRocketMqTransactionProducer;
import com.mlzj.rocketmq.config.MlzjRocketMqProperties;
import com.mlzj.rocketmq.consumer.AbstractMlzjConsumer;
import com.mlzj.rocketmq.producer.AbstractMlzjConcurrentProducer;
import com.mlzj.rocketmq.producer.AbstractMlzjOrderlyProducer;
import com.mlzj.rocketmq.producer.AbstractMlzjTransactionProducer;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * rocketMqProducer初始化处理类
 * @author yhl
 * @date 2019/3/26
 */
public class MlzjRocketMqProducerInitHandler{

    /**
     * 加载注解的属性到producer
     * @param producer producer
     * @param mlzjRocketMqProperties rocketMq配置文件
     */
    public void initConcurrentProducer(AbstractMlzjConcurrentProducer producer, MlzjRocketMqProperties mlzjRocketMqProperties){
        Class<? extends AbstractMlzjConcurrentProducer> producerClass = producer.getClass();
        MlzjRocketMqProducer producerClassAnnotation = producerClass.getAnnotation(MlzjRocketMqProducer.class);
        DefaultMQProducer mqProducer  = producer.getMqProducer();
        mqProducer.setProducerGroup(mlzjRocketMqProperties.getApplicationGroup());
        mqProducer.setNamesrvAddr(mlzjRocketMqProperties.getNameServerAddress());
        mqProducer.setDefaultTopicQueueNums(producerClassAnnotation.queueNumbers());
        mqProducer.setMaxMessageSize(producerClassAnnotation.maxMessageSize());
        mqProducer.setRetryAnotherBrokerWhenNotStoreOK(producerClassAnnotation.retryAnotherBrokerWhenNotStoreOK());
        mqProducer.setCompressMsgBodyOverHowmuch(producerClassAnnotation.compressMsgBodyOverHowMuch());
        mqProducer.setRetryTimesWhenSendAsyncFailed(producerClassAnnotation.retryTimesWhenSendAsyncFailed());
        mqProducer.setRetryTimesWhenSendFailed(producerClassAnnotation.retryTimesWhenSendFailed());
        mqProducer.setSendMsgTimeout(producerClassAnnotation.sendMsgTimeout());
        mqProducer.setInstanceName(producerClass.getName());
        producer.setTags(producerClassAnnotation.tags());
        producer.setTopic(producerClassAnnotation.topic());
    }

    /**
     * 初始化顺序消费
     * @param producer 消息发送类
     * @param mlzjRocketMqProperties rocketMq配置文件
     */
    public void initOrderlyProducer(AbstractMlzjOrderlyProducer producer, MlzjRocketMqProperties mlzjRocketMqProperties) throws IllegalAccessException, InstantiationException {
        Class<? extends AbstractMlzjOrderlyProducer> producerClass = producer.getClass();
        MlzjRocketMqOrderlyProducer producerClassAnnotation = producerClass.getAnnotation(MlzjRocketMqOrderlyProducer.class);
        DefaultMQProducer mqProducer = producer.getMqProducer();
        mqProducer.setProducerGroup(mlzjRocketMqProperties.getApplicationGroup());
        mqProducer.setNamesrvAddr(mlzjRocketMqProperties.getNameServerAddress());
        mqProducer.setDefaultTopicQueueNums(producerClassAnnotation.queueNumbers());
        mqProducer.setMaxMessageSize(producerClassAnnotation.maxMessageSize());
        mqProducer.setRetryAnotherBrokerWhenNotStoreOK(producerClassAnnotation.retryAnotherBrokerWhenNotStoreOK());
        mqProducer.setCompressMsgBodyOverHowmuch(producerClassAnnotation.compressMsgBodyOverHowMuch());
        mqProducer.setRetryTimesWhenSendAsyncFailed(producerClassAnnotation.retryTimesWhenSendAsyncFailed());
        mqProducer.setRetryTimesWhenSendFailed(producerClassAnnotation.retryTimesWhenSendFailed());
        mqProducer.setSendMsgTimeout(producerClassAnnotation.sendMsgTimeout());
        mqProducer.setInstanceName(producerClass.getName());
        producer.setTags(producerClassAnnotation.tags());
        producer.setTopic(producerClassAnnotation.topic());
        Class<?> selectorClass = producerClassAnnotation.messageQueueSelector();
        producer.setMessageQueueSelector((MessageQueueSelector) selectorClass.newInstance());
    }

    /**
     * 初始化事务消息
     * @param producer 生产者
     * @param mlzjRocketMqProperties rocketMq配置文件
     * @param applicationContext applicationContext 容器
     */
    public void initTransactionProducer(AbstractMlzjTransactionProducer producer, MlzjRocketMqProperties mlzjRocketMqProperties, ApplicationContext applicationContext){
        Class<? extends AbstractMlzjTransactionProducer> producerClass = producer.getClass();
        MlzjRocketMqTransactionProducer producerClassAnnotation = producerClass.getAnnotation(MlzjRocketMqTransactionProducer.class);
        TransactionMQProducer transactionMQProducer = producer.getTransactionMQProducer();
        transactionMQProducer.setProducerGroup(mlzjRocketMqProperties.getApplicationGroup());
        transactionMQProducer.setNamesrvAddr(mlzjRocketMqProperties.getNameServerAddress());
        transactionMQProducer.setDefaultTopicQueueNums(producerClassAnnotation.queueNumbers());
        transactionMQProducer.setMaxMessageSize(producerClassAnnotation.maxMessageSize());
        transactionMQProducer.setRetryAnotherBrokerWhenNotStoreOK(producerClassAnnotation.retryAnotherBrokerWhenNotStoreOK());
        transactionMQProducer.setCompressMsgBodyOverHowmuch(producerClassAnnotation.compressMsgBodyOverHowMuch());
        transactionMQProducer.setRetryTimesWhenSendAsyncFailed(producerClassAnnotation.retryTimesWhenSendAsyncFailed());
        transactionMQProducer.setRetryTimesWhenSendFailed(producerClassAnnotation.retryTimesWhenSendFailed());
        transactionMQProducer.setSendMsgTimeout(producerClassAnnotation.sendMsgTimeout());
        transactionMQProducer.setInstanceName(producerClass.getName());
        transactionMQProducer.setTransactionListener(applicationContext.getBean(producerClassAnnotation.transactionalListener()));
        producer.setTags(producerClassAnnotation.tags());
        producer.setTopic(producerClassAnnotation.topic());
    }

    /**
     *
     * @param consumer 消费者
     * @param mlzjRocketMqProperties rocketMQ 配置文件
     * @param applicationContext applicationContext 容器
     */
    public void initCommonConsumer(AbstractMlzjConsumer consumer, MlzjRocketMqProperties mlzjRocketMqProperties,ApplicationContext applicationContext){
        DefaultMQPushConsumer mqPushConsumer = consumer.getMqPushConsumer();
        MlzjRocketMqConsumer consumerClassAnnotation = consumer.getClass().getAnnotation(MlzjRocketMqConsumer.class);
        mqPushConsumer.setConsumeConcurrentlyMaxSpan(consumerClassAnnotation.consumeConcurrentlyMaxSpan());
        mqPushConsumer.setConsumeFromWhere(consumerClassAnnotation.consumeFromWhere());
        mqPushConsumer.setConsumeMessageBatchMaxSize(consumerClassAnnotation.consumeMessageBatchMaxSize());
        mqPushConsumer.setConsumerGroup(mlzjRocketMqProperties.getApplicationGroup());
        mqPushConsumer.setConsumeThreadMax(consumerClassAnnotation.consumeThreadMax());
        mqPushConsumer.setConsumeThreadMin(consumerClassAnnotation.consumeThreadMin());
        mqPushConsumer.setMessageListener(applicationContext.getBean(consumerClassAnnotation.messageListener()));
        mqPushConsumer.setPullBatchSize(consumerClassAnnotation.pullBatchSize());
        mqPushConsumer.setPullInterval(consumerClassAnnotation.pullInterval());
        mqPushConsumer.setPullThresholdForQueue(consumerClassAnnotation.pullThresholdForQueue());
        mqPushConsumer.setPullThresholdForTopic(consumerClassAnnotation.pullThresholdForTopic());
        mqPushConsumer.setPullThresholdSizeForQueue(consumerClassAnnotation.pullThresholdSizeForQueue());
        mqPushConsumer.setPullThresholdSizeForTopic(consumerClassAnnotation.pullThresholdSizeForTopic());
        mqPushConsumer.setPostSubscriptionWhenPull(consumerClassAnnotation.postSubscriptionWhenPull());
        mqPushConsumer.setUnitMode(consumerClassAnnotation.unitMode());
        mqPushConsumer.setAdjustThreadPoolNumsThreshold(consumerClassAnnotation.adjustThreadPoolNumsThreshold());
        mqPushConsumer.setMaxReconsumeTimes(consumerClassAnnotation.maxReconsumeTimes());
        mqPushConsumer.setSuspendCurrentQueueTimeMillis(consumerClassAnnotation.suspendCurrentQueueTimeMillis());
        mqPushConsumer.setConsumeTimeout(consumerClassAnnotation.consumeTimeout());
        mqPushConsumer.setInstanceName(consumer.getClass().getName());
        mqPushConsumer.setNamesrvAddr(mlzjRocketMqProperties.getNameServerAddress());
        mqPushConsumer.setUnitMode(consumerClassAnnotation.unitMode());
        mqPushConsumer.setMessageModel(consumerClassAnnotation.messageModel());
        String[] subscriptions = consumerClassAnnotation.subscription();
        Map<String, String> subscriptionMap = new HashMap<>(4);
        for (String subscription : subscriptions){
            String[] subscriptionSplit = StringUtils.split(subscription, ",");
            subscriptionMap.put(subscriptionSplit[0],subscriptionSplit[1]);
        }
        mqPushConsumer.setSubscription(subscriptionMap);
    }
}
