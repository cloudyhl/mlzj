package com.mlzj.zookeeper.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author yhl
 * @date 2019/4/1
 */
public class ZookeeperClientDemo {



    public static void main(String[] args) throws Exception {
        String zookeeperClientUrl = "47.94.162.134:2181";
        CuratorFramework curatorClient = CuratorFrameworkFactory.builder().connectString(zookeeperClientUrl)
                .connectionTimeoutMs(2000)
                .sessionTimeoutMs(10000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .namespace("root")
                .build();
        curatorClient.start();
        String s = new String(curatorClient.getData().forPath("/mdb"));
        System.out.println(s);
    }
}
