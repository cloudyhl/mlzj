package com.mlzj.zookeeper.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * @author yhl
 * @date 2019/4/9
 */
@Component
@Getter
public class ThreadPool {

    private ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    private void init(){
        int processors = Runtime.getRuntime().availableProcessors();
        this.threadPoolExecutor = new ThreadPoolExecutor(processors, processors * 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), Executors.defaultThreadFactory());
    }
}
