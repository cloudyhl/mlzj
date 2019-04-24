package com.mlzj.common.demo.blockqueue;

import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2019/4/12
 */
@Data
public class DelayTask<T extends Callable> implements Delayed {

    /**
     * 具体任务
     */
    private T task;

    /**
     * 延时
     */
    private long expireTime;


    public DelayTask(T task, long timeOut) {
        this.task = task;
        this.expireTime = System.currentTimeMillis() + timeOut;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(),unit);
    }

    @Override
    public int compareTo(Delayed other) {
        if (other == this){
            return 0;
        }
        if (other instanceof DelayTask){
            DelayTask delayTask = (DelayTask)other;
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
}
