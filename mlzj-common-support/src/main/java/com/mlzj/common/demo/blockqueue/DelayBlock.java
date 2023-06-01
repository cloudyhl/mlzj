package com.mlzj.common.demo.blockqueue;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 *DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue是一个没有大小限制的队列
 * @author yhl
 * @date 2019/4/12
 */
public class DelayBlock {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayTask<TaskRun>(new TaskRun(1),1000));
        delayQueue.add(new DelayTask<TaskRun>(new TaskRun(2),2000));
        delayQueue.add(new DelayTask<TaskRun>(new TaskRun(3),3000));
        ThreadPool threadPool = new ThreadPool();
        ThreadPoolExecutor threadPoolTaskExecutor = threadPool.getThreadPool();
        DelayTask<TaskRun> take = delayQueue.take();
        FutureTask<TaskRun> taskRunFutureTask = new FutureTask<>(take.getTask());
        threadPoolTaskExecutor.submit(taskRunFutureTask);
        DelayTask<TaskRun> take1 = delayQueue.take();
        FutureTask<TaskRun> taskRunFutureTask1 = new FutureTask<>(take1.getTask());
        threadPoolTaskExecutor.submit(taskRunFutureTask1);
        DelayTask<TaskRun> take2 = delayQueue.take();
        FutureTask<TaskRun> taskRunFutureTask2 = new FutureTask<>(take2.getTask());
        threadPoolTaskExecutor.submit(taskRunFutureTask2);
        threadPoolTaskExecutor.shutdown();
    }
}
