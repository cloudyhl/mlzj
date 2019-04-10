package com.mlzj.zookeeper.model;

import lombok.Data;
import org.apache.curator.framework.CuratorFramework;

/**
 * @author yhl
 * @date 2019/4/8
 */
@Data
public class ZLock {
    /**
     * 是否加锁成功
     */
    private boolean locked;

    /**
     * zookeeper连接
     */
    private CuratorFramework zkClient;

    /**
     * 锁节点路径
     */
    private String path;
}
