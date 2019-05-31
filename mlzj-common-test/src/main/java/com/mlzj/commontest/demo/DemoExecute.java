package com.mlzj.commontest.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Component
public class DemoExecute {


    @Async
    public void execute(){
        System.out.println("go in");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute");
    }

}
