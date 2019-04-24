package com.mlzj.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjCommonSupportApplicationTests {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();
    Condition condition1 = reentrantLock.newCondition();
    @Test
    public void contextLoads() {
    }


    @Test
    public void concurrentBlockQueue(){
        ConcurrentLinkedQueue<Integer> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        concurrentLinkedQueue.add(1);
        concurrentLinkedQueue.add(2);
        concurrentLinkedQueue.add(3);
        concurrentLinkedQueue.add(4);
        concurrentLinkedQueue.add(5);
        System.out.println(concurrentLinkedQueue.peek());
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.contains(1));
        System.out.println(concurrentLinkedQueue);
        Thread.currentThread().setName(UUID.randomUUID().toString());
        System.out.println(Thread.currentThread().getName());
    }

    class ThreadFactorys implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            new Thread(r,UUID.randomUUID().toString());
            return null;
        }
    }

    @Test
    public void reentrantLock() throws InterruptedException {
        method1();
        method2();
        TimeUnit.SECONDS.sleep(2);
    }

    private void method1(){
        new Thread(()->{
            reentrantLock.lock();
            try {
                System.out.println("method1 run");
                condition.await();
                System.out.println("method1 continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void method2(){
        new Thread(()->{
            reentrantLock.lock();
            System.out.println("method2 run");
            condition1.signal();
            reentrantLock.unlock();
        }).start();
    }


}

