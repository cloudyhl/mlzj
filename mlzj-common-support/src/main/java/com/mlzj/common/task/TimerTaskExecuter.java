package com.mlzj.common.task;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 延时任务执行器
 *
 * @author yhl
 * @date yhl
 */
@Slf4j
@Data
public class TimerTaskExecuter<T extends AbstractTimerTask> {

    private DelayQueue<T> delayQueue = new DelayQueue<>();

    private Thread executeThread;

    /**
     * 增加一个任务
     */
    public void addTimerTask(T task) {
        if (task == null) {
            throw new NullPointerException();
        }
        this.delayQueue.add(task);
    }


    public void start() throws InterruptedException {
        this.start(false);
    }

    /**
     * 开始执行任务
     * @param retry 是否重试
     */
    public void start(boolean retry) throws InterruptedException {
        executeThread = Thread.currentThread();
        while (true) {
            T task = null;
            try {
                task = delayQueue.take();
                task.execute();
            } catch (Exception e) {
                if (task != null && retry) {
                    AtomicInteger errorTime = task.getErrorTime();
                    errorTime.addAndGet(1);
                    if (errorTime.get() < 3) {
                        this.delayQueue.add(task);
                    } else {
                        log.error("重试3次后任务仍然执行失败:{}", JSON.toJSONString(task), e);
                    }
                }else {
                    log.error("执行失败:{}", JSON.toJSONString(task), e);
                }
            }
        }
    }

    /**
     * 暂停任务执行
     */
    public synchronized void stop(){
        executeThread.interrupt();
    }

}
