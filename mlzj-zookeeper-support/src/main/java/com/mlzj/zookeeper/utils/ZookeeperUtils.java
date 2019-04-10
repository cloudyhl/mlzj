package com.mlzj.zookeeper.utils;

import com.mlzj.zookeeper.config.ZookeeperProperties;
import com.mlzj.zookeeper.model.AbstractBaseNodeCacheListener;
import com.mlzj.zookeeper.model.ZBlockingLock;
import com.mlzj.zookeeper.model.ZLock;
import com.mlzj.zookeeper.model.ZLockTimerTask;
import com.mlzj.zookeeper.task.ZLockTimerTaskExecuter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * zookeeper工具类
 *
 * @author yhl
 * @date 2019/4/3
 */
@Component
@EnableConfigurationProperties(ZookeeperProperties.class)
@Slf4j
public class ZookeeperUtils {

    @Resource
    private ZookeeperProperties zookeeperProperties;

    @Resource
    private ThreadPool threadPool;

    @Resource
    private ZLockTimerTaskExecuter zLockTimerTaskExecuter;

    private CuratorFramework curatorFramework;

    public CuratorFramework getClient() {
        return curatorFramework;
    }

    @PostConstruct
    void initZkClient() {
        curatorFramework = CuratorFrameworkFactory.builder().connectString(zookeeperProperties.getZkUrl())
                .connectionTimeoutMs(zookeeperProperties.getConnectionTimeoutMs())
                .sessionTimeoutMs(zookeeperProperties.getSessionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zookeeperProperties.getRetryTimeMs(), zookeeperProperties.getMaxRetries()))
                .namespace(zookeeperProperties.getNameSpace())
                .build();
        curatorFramework.start();
    }

    /**
     * 获取InterProcessMutex锁
     *
     * @param path 节点路径
     * @return InterProcessMutex锁
     */
    public InterProcessMutex getInterProcessMutex(String path) {
        return new InterProcessMutex(curatorFramework, path);
    }

    /**
     * 获取DistributedBarrier
     *
     * @param path 节点路径
     * @return 获取DistributedBarrier
     */
    public DistributedBarrier getDistributedBarrier(String path) {
        return new DistributedBarrier(curatorFramework, path);
    }

    /**
     * 获取DistributedAtomicInteger
     *
     * @param path 节点路劲
     * @return DistributedAtomicInteger
     */
    public DistributedAtomicInteger getDistributedAtomicInteger(String path) {
        return new DistributedAtomicInteger(curatorFramework, path, new ExponentialBackoffRetry(1000, 3));
    }


    /**
     * 加锁
     *
     * @param path 节点路劲
     */
    public ZLock lock(String path) {
        return this.lock(path, null);
    }

    /**
     * 加锁,延时解锁
     *
     * @param path 节点路劲
     */
    public ZLock lock(String path, Long waitTime) {
        ZLock zLock = new ZLock();
        try {
            Stat stat = this.curatorFramework.checkExists().forPath(path);
            if (Objects.nonNull(stat)) {
                zLock.setLocked(false);
                log.info("获取锁失败,节点已存在,节点信息{}", path);
                return zLock;
            }
            this.curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(path);
            zLock.setLocked(true);
            zLock.setPath(path);
            zLock.setZkClient(this.curatorFramework);
            //执行延时解锁
            if (waitTime != null) {
                zLockTimerTaskExecuter.newTimeout(new ZLockTimerTask(zLock), waitTime, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            log.error("获取锁失败,节点信息{}", path, e);
            zLock.setLocked(false);
        }
        return zLock;
    }

    /**
     * 解锁
     *
     * @param zLock 锁
     * @param path  节点路径
     */
    public void unLock(ZLock zLock, String path) {
        try {
            Stat stat = this.curatorFramework.checkExists().forPath(path);
            if (Objects.isNull(stat)) {
                log.info("释放锁失败,节点不存在,节点信息{}", path);
                return;
            }
            this.curatorFramework.delete().forPath(path);
            zLock.setLocked(false);
        } catch (Exception e) {
            log.error("解锁失败,节点信息{}", path, e);
        }
    }

    /**
     * 获取nodeCache
     *
     * @param path 需要监听的节点路径
     */
    public void getNodeCache(String path, AbstractBaseNodeCacheListener nodeCacheListener) throws Exception {
        NodeCache nodeCache = new NodeCache(this.curatorFramework, path, false);
        nodeCacheListener.setNodeCache(nodeCache);
        nodeCache.getListenable().addListener(nodeCacheListener, threadPool.getThreadPoolExecutor());
        nodeCache.start(true);
    }

    /**
     * 获得PathChildrenCache
     *
     * @param path                      节点路劲
     * @param pathChildrenCacheListener 节点变更监听器
     * @param startMode                 初始化类型
     */
    public PathChildrenCache getPathChildrenCache(String path, PathChildrenCacheListener pathChildrenCacheListener, PathChildrenCache.StartMode startMode) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(this.curatorFramework,path,true);
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener,threadPool.getThreadPoolExecutor());
        pathChildrenCache.start(startMode);
        return pathChildrenCache;
    }

    /**
     * 获取阻塞锁
     * @param path 节点路径
     * @return 阻塞锁
     */
    public ZBlockingLock getBlockingLock(String path){
        return new ZBlockingLock(this.curatorFramework,path);
    }

}
