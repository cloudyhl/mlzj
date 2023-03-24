package com.mlzj.commontest.demo.forkjoin;

import java.util.concurrent.*;

/**
 * @author yhl
 * @date 2020/4/21
 */
public class TaskPoolExecutor {

    private static final ThreadPoolExecutor threadPoolExecutor;

    static {
        threadPoolExecutor  = new ThreadPoolExecutor(16, 32, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), Executors.defaultThreadFactory());
    }

    public static ThreadPoolExecutor gerThreadPool(){
        return threadPoolExecutor;
    }

    public static void main(String[] args) {
        int i = 20;
    }
}
