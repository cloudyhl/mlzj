package com.mlzj.common.demo.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * arrayBlockQueue 定长阻塞队列,队列中指定长度之后则不可再增加
 * BlockQueue 核心 take  put  take获取数据若队列为空则阻塞,put放入数据若队列满则阻塞
 * @author yhl
 * @date 2019/4/12
 */
public class ArrayBlock {
    public static void main(String[] args) throws Exception{

    }
    public static void arrayBlockQueue() throws InterruptedException {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        //poll方法拉取数据若等待时间队列为空则返回null
        System.out.println(arrayBlockingQueue.poll(1000, TimeUnit.MILLISECONDS));
        arrayBlockingQueue.poll();
        arrayBlockingQueue.add(1);
        arrayBlockingQueue.add(2);
        new Thread(()->{
            try {
                Thread.sleep(3000);
                arrayBlockingQueue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        arrayBlockingQueue.put(3);
        System.out.println(arrayBlockingQueue);
    }
}
