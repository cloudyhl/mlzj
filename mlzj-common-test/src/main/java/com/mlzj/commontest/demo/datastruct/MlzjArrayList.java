package com.mlzj.commontest.demo.datastruct;

import com.mlzj.commontest.demo.datastruct.abstracts.AbstractMlzjList;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjCollections;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjIteration;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjList;
import com.mlzj.commontest.demo.datastruct.iterators.MlzjArrayListIterator;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yhl
 * @date 2019/10/10
 */
@SuppressWarnings("all")
public class MlzjArrayList<T> extends AbstractMlzjList<T> implements MlzjList<T> {

    private T[] dataArray;

    private int size;

    private AtomicInteger index = new AtomicInteger(0);

    private float capacityMultiply = 0.75F;

    /**
     * 新增元素
     *
     * @param element 元素
     * @return 自己
     */
    @Override
    public MlzjArrayList add(T element) {
        needMoreCapacity();
        dataArray[index.intValue()] = element;
        size = index.addAndGet(1);
        return this;
    }

    @Override
    public synchronized MlzjCollections<T> addSafe(T element) {
        return this.add(element);
    }

    /**
     * 获取线性表的存在数据的长度
     *
     * @return 长度
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 构造方法默认长度为5
     */
    public MlzjArrayList() {
        this.dataArray = (T[]) new Object[5];
    }

    /**
     * 构造方法
     *
     * @param Capacity 指定容量
     */
    public MlzjArrayList(int Capacity) {
        this.dataArray = (T[]) new Object[Capacity];
    }


    /**
     * 获取该下标的值
     *
     * @param index 数据下标
     * @return 下标的该下标的数据
     */
    @Override
    public T get(int index) {
        this.checkIndex(index);
        return dataArray[index];
    }

    @Override
    public synchronized void set(int index, T element) {
        this.checkIndex(index);
        dataArray[index] = element;
    }

    @Override
    public synchronized void remove(T element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(dataArray[index], element)) {
                for (int start = index; start < size; start++){
                    dataArray[start] = dataArray[start + 1];
                }
                dataArray[this.index.intValue()] = null;
                size = this.index.addAndGet(-1);
                index--;
            }
        }
    }



    /**
     * 获取线性表的迭代器
     *
     * @return 线性表的迭代器
     */
    @Override
    public MlzjIteration<T> createIteration() {
        return new MlzjArrayListIterator<>(this);
    }


    /**
     * 判断是否需要扩容，如果需要扩容则调用扩容方法
     */
    private synchronized void needMoreCapacity() {
        if (index.intValue() + 1 > dataArray.length) {
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

    /**
     * 检验传入的index是否合法
     *
     * @param index 下标索引
     */
    private void checkIndex(int index) {
        if (index >= this.index.intValue() || index < 0) {
            throw new IndexOutOfBoundsException("index is bigger than this array's length or smaller than zero");
        }
    }

}
