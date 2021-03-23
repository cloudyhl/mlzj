package com.mlzj.commontest.demo.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 模拟主方法
 * @author yhl
 * @date 2020/4/21
 */
public class MainService {

    public Integer go(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int index = 0; index < 10000; index++){
            list.add(index);
            sum = sum + index;
        }
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new PullTask(list));
        forkJoinPool.shutdown();
        System.out.println(submit.get());
    }
}
