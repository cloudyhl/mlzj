package com.mlzj.common.demo.blockqueue;

import java.util.concurrent.Callable;

/**
 * @author yhl
 * @date 2019/4/12
 */
public class TaskRun implements Callable {

    private int number;

    public TaskRun(int number) {
        this.number = number;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(this.number);
        return number;
    }
}
