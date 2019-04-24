package com.mlzj.common.task;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yhl
 * @date 2019/4/12
 */
@Data
public abstract class AbstractTimerTask implements Delayed {
    /**
     * 延时
     */
    private long expireTime;

    /**
     * 错误次数
     */
    protected AtomicInteger errorTime = new AtomicInteger(0);

    /**
     * 延时任务需要执行的方法
     */
    public abstract void execute();

    public AbstractTimerTask(long timeOut) {
        this.expireTime = System.currentTimeMillis() + timeOut;
    }


    @Override
    public int compareTo(Delayed other) {
        if (other == this){
            return 0;
        }
        if (other instanceof AbstractTimerTask){
            AbstractTimerTask delayTask = (AbstractTimerTask)other;
            long compareTime = this.expireTime - delayTask.expireTime;
            if (compareTime > 0){
                return 1;
            }else if (compareTime == 0){
                return 0;
            }else if (compareTime < 0){
                return -1;
            }
        }
        long time = (getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS));
        return (time == 0) ? 0 : ((time < 0) ? -1 : 1);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(),unit);
    }
}
