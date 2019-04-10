package com.mlzj.zookeeper.model;

import com.mlzj.zookeeper.task.ZLockTimerTaskExecuter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2019/4/10
 */
@Slf4j
@Getter
public class ZBlockingLock {


    /**
     * zk连接
     */
    private CuratorFramework client;

    /**
     * 节点路径
     */
    private String path;

    /**
     * 是否锁成功
     */
    private boolean locked;


    public ZBlockingLock(CuratorFramework client, String path) {
        this.client = client;
        this.path = path;
    }

    /**
     * 阻塞锁，加锁
     */


    public synchronized void lock() {
        lock(null, null);
    }

    public synchronized void lock(Long waitTime, ZLockTimerTaskExecuter zLockTimerTaskExecuter) {
        while (true) {
            try {
                Stat stat = this.client.checkExists().usingWatcher((CuratorWatcher) watchedEvent -> {
                    if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                        this.notifyThisWait();
                    }
                }).forPath(this.path);
                if (stat != null) {
                    this.wait();
                    this.locked = false;
                } else {
                    client.create().withMode(CreateMode.EPHEMERAL).forPath(this.path);
                    this.locked = true;
                    if (waitTime != null) {
                        zLockTimerTaskExecuter.newTimeout(new ZBlockingLockTimerTask(this), waitTime, TimeUnit.MILLISECONDS);
                    }
                    break;
                }
            } catch (Exception e) {
                log.error("加锁失败{},节点信息{}", this.path, e);
                this.locked = false;
                break;
            }
        }
    }

    /**
     * 唤醒正在等在锁的线程
     */
    private synchronized void notifyThisWait() {
        this.notifyAll();
    }

    /**
     * 解锁
     */
    public void unlock() {
        try {
            Stat stat = this.client.checkExists().forPath(this.path);
            if (stat != null){
                this.client.delete().forPath(this.path);
            }
        } catch (Exception e) {
            log.error("解锁失败,节点信息{}", this.path, e);
        }
    }

}
