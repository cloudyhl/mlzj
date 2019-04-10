package com.mlzj.zookeeper.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

/**
 * @author yhl
 * @date 2019/4/10
 */
@Slf4j
public class PathNodeChangeListener implements PathChildrenCacheListener {
    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
        switch (pathChildrenCacheEvent.getType()){
            case CHILD_ADDED:
                log.info("增加节点");
                break;
            case INITIALIZED:
                log.info("初始化");
                break;
            case CHILD_REMOVED:
                log.info("节点移除");
                break;
            case CHILD_UPDATED:
                log.info("节点更新");
                break;
            case CONNECTION_LOST:
                log.info("连接断开");
                break;
            case CONNECTION_SUSPENDED:
                log.info("连接中断");
                break;
            case CONNECTION_RECONNECTED:
                log.info("重新连接");
                break;
            default:
                log.info("没有发生任何事");
                break;
        }
    }
}
