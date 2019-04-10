package com.mlzj.zookeeper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.TimerTask;

import java.util.Objects;

/**
 * 定时解锁ZLock
 * @author yhl
 * @date 2019/4/9
 */
@Data
@AllArgsConstructor
public class ZLockTimerTask implements TimerTask {

    /**
     * 锁
     */
    private ZLock zLock;

    @Override
    public void run(Timeout timeout) throws Exception {
        if (Objects.nonNull(zLock.getZkClient().checkExists().forPath(zLock.getPath()))){
            zLock.getZkClient().delete().forPath(zLock.getPath());
        }
    }
}
