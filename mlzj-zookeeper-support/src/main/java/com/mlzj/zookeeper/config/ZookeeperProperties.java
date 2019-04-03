package com.mlzj.zookeeper.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * zookeeper 配置
 * @author yhl
 * @date 2019/4/3
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "mlzj.zookeeper")
public class ZookeeperProperties {
    /**
     * zk的连接地址
     */
    private String zkUrl;
    /**
     * 连接超时时间
     */
    private  int connectionTimeoutMs;

    /**
     * 命名空间
     */
    private String nameSpace;

    /**
     * 重试间隔时间
     */
    private int retryTimeMs;

    /**
     * 最大重试次数
     */
    private int maxRetries;

    /**
     * 回话过期时间
     */
    private int sessionTimeoutMs;
}
