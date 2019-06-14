package com.mlzj.cloud.client.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * RefreshScope 该注解表示动态刷新该配置,需要暴露端点refresh手动调用才可刷新
 * @author yhl
 * @date 2019/6/10
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "mlzj.client")
public class MlzjConfigProperties {
    private String message;

}
