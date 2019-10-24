package com.mlzj.commontest.demo.datastruct.interfaces;

/**
 * @author yhl
 * @date 2019/10/10
 */
public interface MlzjList<T> extends MlzjCollections<T>{

    /**
     * 获取该index的值
     * @param index 索引下标
     * @return 返回值
     */
    T get(int index);

    /**
     * 设置这个位置的值
     * @param index 索引下标
     * @param element 元素
     */
    void set(int index, T element);

    /**
     * 溢出元素
     * @param element 元素
     */
    void remove(T element);






}
