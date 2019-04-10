package com.mlzj.zookeeper.model;

import lombok.Data;
import org.apache.zookeeper.data.Stat;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * 自动释放锁
 * @author yhl
 * @date
 */
@Data
public class ZLockTimeRelease implements Callable<ZLock>, Serializable {

    private static final long serialVersionUID = 7232388530935061010L;
    /**
     * 锁等待时间
     */
    private long waitTime;

    /**
     * zookeeper锁
     */
    private ZLock zLock;

    /**
     * 过期时间
     */
    private long expiredTime;

    public ZLockTimeRelease(long waitTime, ZLock zLock) {
        this.waitTime = waitTime;
        this.zLock = zLock;
        this.expiredTime = System.currentTimeMillis()+waitTime;
    }


    @Override
    public ZLock call() throws Exception {
        Thread.sleep(this.waitTime);
        Stat stat = zLock.getZkClient().checkExists().forPath(zLock.getPath());
        if (Objects.nonNull(stat)){
            zLock.getZkClient().delete().forPath(zLock.getPath());
            zLock.setLocked(false);
        }
        return null;
    }
}
