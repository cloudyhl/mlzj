package com.mlzj.rocketmq.demo;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author yhl
 * @date 2019/3/26
 */
public class DemoOrderLySelector implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        Integer id = (Integer)arg;
        int finalId = id % mqs.size();
        return mqs.get(finalId);
    }
}
