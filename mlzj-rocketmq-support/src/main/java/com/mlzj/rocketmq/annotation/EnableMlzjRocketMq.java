package com.mlzj.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * 是否启用rocketMq
 *
 * @author yhl
 * @date 2019/3/26
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableMlzjRocketMq {
}
