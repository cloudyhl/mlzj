package com.mlzj.rocketmq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * rocketMq配置文件
 * @author yhl
 * @date 2019/3/26
 */
@ConfigurationProperties(prefix = "mlzj.rocketmq")
@Getter
@Setter
public class MlzjRocketMqProperties {

    /**
     * nameSrvAddress
     */
    private String nameServerAddress;

    /**
     * 应用所属组
     */
    private String applicationGroup;
}
