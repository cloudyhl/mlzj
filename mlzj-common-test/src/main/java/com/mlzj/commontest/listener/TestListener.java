package com.mlzj.commontest.listener;

import com.mlzj.commontest.event.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2019/4/26
 */
//@Component
public class TestListener implements ApplicationListener<TestEvent> {


    @Override
    @Async
    public void onApplicationEvent(@NonNull TestEvent event) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(event.getEventName());
        System.out.println(event.getEventString());
    }
}
