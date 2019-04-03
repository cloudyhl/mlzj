package com.mlzj.rocketmq.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * rocketMq消息类型
 * @author yhl
 * @date 2019/3/26
 */
@AllArgsConstructor
@Getter
public enum ProducerTypeEnum {
    /**
     *
     */
    CONCURRENT("concurrent","默认并发消息发送"),
    ORDERLY("orderly","顺序"),
    ;

    /**
     * 类型
     */
    private String type;
    /**
     * 描述
     */
    private String describe;
}
