package com.mlzj.commontest.demo.concurrent.test;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2020/4/2
 */
@Data
public class LockObject {

    private Integer lock = 1;

    public synchronized void go() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        this.lock++;
    }

    public synchronized void get(){
        System.out.println(lock);
    }
}
