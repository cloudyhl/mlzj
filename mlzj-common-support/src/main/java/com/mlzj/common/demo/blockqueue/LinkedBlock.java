package com.mlzj.common.demo.blockqueue;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * 再删除元素时会生成一个node节点,需要GC进行垃圾回收
 * @author yhl
 * @date 2019/4/12
 */
public class LinkedBlock {
    public static void main(String[] args) throws Exception {
        linkedBlockingQueue();
    }

    private static void linkedBlockingQueue() throws InterruptedException {
        LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>(3);
        linkedBlockingDeque.put(1);
        linkedBlockingDeque.put(2);
        linkedBlockingDeque.put(3);
        System.out.println(linkedBlockingDeque);
    }
}
