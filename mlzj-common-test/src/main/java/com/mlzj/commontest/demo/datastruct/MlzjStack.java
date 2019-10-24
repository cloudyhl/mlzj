package com.mlzj.commontest.demo.datastruct;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yhl
 * @date 2019/10/14
 */
@SuppressWarnings("all")
public class MlzjStack<T> {

    private T[] dataArray;

    private int size;

    private AtomicInteger top = new AtomicInteger(0);

    private float capacityMultiply = 0.75F;

    /**
     * 构造方法默认长度为5
     */
    public MlzjStack() {
        this.dataArray = (T[]) new Object[5];
    }

    /**
     * 构造方法
     *
     * @param Capacity 指定容量
     */
    public MlzjStack(int Capacity) {
        this.dataArray = (T[]) new Object[Capacity];
    }

    /**
     * 压栈
     * @param element 新增的元素
     * @return 自身方便继续增加元素
     */
    public MlzjStack<T> push(T element){
        this.needMoreCapacity();
        dataArray[top.intValue()] = element;
        size = top.incrementAndGet();
        return this;
    }

    /**
     * 出栈
     * @return 出栈
     */
    public T pop(){
        if (top.intValue() - 1 < 0){
            throw new IndexOutOfBoundsException("pop stack error, index well is smaller than zero");
        }
        T value = this.dataArray[top.decrementAndGet()];
        size = top.intValue();
        return value;
    }


    /**
     * 查询栈顶元素 但不出栈
     * @return 栈顶元素
     */
    public T peek(){
        return this.dataArray[size-1];
    }
    /**
     * 判断是否需要扩容，如果需要扩容则调用扩容方法
     */
    private synchronized void needMoreCapacity() {
        if (top.intValue() + 1 > dataArray.length) {
            copyOf();
        }
    }

    /**
     * 对线性表的扩容方法
     */
    private void copyOf() {
        int newDataArrayLength = (int) (dataArray.length / capacityMultiply);
        T[] newdataArray = (T[]) new Object[newDataArrayLength];
        for (int index = 0; index < dataArray.length; index++) {
            newdataArray[index] = dataArray[index];
        }
        this.dataArray = newdataArray;
    }

    public int size(){
        return size;
    }
}
