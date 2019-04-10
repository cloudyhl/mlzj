package com.mlzj.zookeeper.task;

import org.jboss.netty.util.HashedWheelTimer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ZLockTimerTask 执行器
 * @author yhl
 * @date 2019/4/9
 */
@Component
public class ZLockTimerTaskExecuter extends HashedWheelTimer {

    @PostConstruct
    private void init(){
        this.start();
    }
}
