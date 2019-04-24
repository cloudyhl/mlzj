package com.mlzj.common.demo.blockqueue;

import com.mlzj.common.task.TimerTaskExecuter;

import java.util.concurrent.TimeUnit;

/**
 * 测试timerTask
 * @author yhl
 * @date 2019/4/12
 */
public class TimerTaskTest {

    public static void main(String[] args) throws InterruptedException {
        TimerTaskExecuter<TimerTaskTestVo> taskExecuter = new TimerTaskExecuter<>();
        new Thread(()->{
            try {
                Thread.sleep(3000);
                taskExecuter.addTimerTask(new TimerTaskTestVo(1,2000));
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        taskExecuter.start(true);
        System.out.println(1);

    }
}
