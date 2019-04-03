package com.mlzj.rocketmq.demo;

import com.mlzj.rocketmq.annotation.MlzjRocketMqConsumer;
import com.mlzj.rocketmq.consumer.AbstractMlzjConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/3/27
 */
@Component
@Slf4j
@MlzjRocketMqConsumer(messageListener = DemoMessageListener.class,
        subscription = "WeiXintest,*",
        consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET)
public class DemoConcurrentConsumer extends AbstractMlzjConsumer {
}
