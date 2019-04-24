package com.mlzj.zookeeper.utils;

import lombok.Getter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author yhl
 * @date 2019/4/9
 */
@Component
@Getter
public class ThreadPool {

    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @PostConstruct
    private void init() {
        int processors = Runtime.getRuntime().availableProcessors();
        this.threadPoolExecutor = new ThreadPoolExecutor(processors, processors * 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), Executors.defaultThreadFactory());
        threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        threadPoolTaskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        //线程池维护线程的最大数量
        threadPoolTaskExecutor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);
        //线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(100);
        //线程池所使用的缓冲队列大小
        threadPoolTaskExecutor.setQueueCapacity(100);
        //拒绝策略ThreadPoolExecutor.AbortPolicy()抛出java.util.concurrent.RejectedExecutionException异常
        //CallerRunsPolicy用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        //DiscardPolicy：不能执行的任务将被删除只不过他不抛出异常。
        //DiscardOldestPolicy如果执行程序尚未关闭，则位于工作队列头部的任务将被删除，然后重试执行程序（如果再次失败，则重复此过程）
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
    }
}
