package com.mlzj.rocketmq.annotation;

import java.lang.annotation.*;

/**
 * @author yhl
 * @date 2019/3/25
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConcurrentProducer {
    /**
     * 初始队里数
     */
    int queueNumbers() default 0;

    /**
     * 主题名称
     */
    String topic();

    /**
     * 标签
     */
    String tags();
}
