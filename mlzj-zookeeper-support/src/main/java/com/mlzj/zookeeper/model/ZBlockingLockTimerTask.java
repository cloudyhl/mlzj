package com.mlzj.zookeeper.model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.zookeeper.data.Stat;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;

/**
 * @author yhl
 * @date 2019/4/10
 */
@Setter
@AllArgsConstructor
public class ZBlockingLockTimerTask implements TimerTask {

    /**
     * 阻塞锁
     */
    private ZBlockingLock zBlockingLock;

    @Override
    public void run(Timeout timeout) throws Exception {
        Stat stat = zBlockingLock.getClient().checkExists().forPath(zBlockingLock.getPath());
        if (stat != null){
            zBlockingLock.getClient().delete().forPath(zBlockingLock.getPath());
        }
    }
}
