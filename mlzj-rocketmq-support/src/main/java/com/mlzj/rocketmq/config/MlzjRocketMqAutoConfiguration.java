package com.mlzj.rocketmq.config;

import com.mlzj.rocketmq.annotation.EnableMlzjRocketMq;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author yhl
 * @date 2019/3/26
 */
@Configuration
@ConditionalOnBean(annotation = EnableMlzjRocketMq.class)
@Order(10000)
public class MlzjRocketMqAutoConfiguration {
}
