package com.mlzj.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * zookeeper工具类
 * @author yhl
 * @date 2019/4/3
 */
@Component
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperUtils {

    @Resource
    private ZookeeperProperties zookeeperProperties;

    private CuratorFramework curatorFramework;

    public CuratorFramework getClient(){
        return  curatorFramework;
    }

    @PostConstruct
    void initZkClient(){
        curatorFramework = CuratorFrameworkFactory.builder().connectString(zookeeperProperties.getZkUrl())
                .connectionTimeoutMs(zookeeperProperties.getConnectionTimeoutMs())
                .sessionTimeoutMs(zookeeperProperties.getSessionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zookeeperProperties.getRetryTimeMs(),zookeeperProperties.getMaxRetries()))
                .namespace(zookeeperProperties.getNameSpace())
                .build();
        curatorFramework.start();
    }
}
