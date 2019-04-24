package com.mlzj.common.demo.blockqueue;

import lombok.Getter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yhl
 */
@Getter
public class ThreadPool {

    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public ThreadPool() {
        this.threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setQueueCapacity(50);
        threadPoolTaskExecutor.setCorePoolSize(8);
        threadPoolTaskExecutor.setMaxPoolSize(16);
        threadPoolTaskExecutor.setKeepAliveSeconds(100);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
    }
}
