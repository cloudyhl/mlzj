package com.mlzj.commontest.demo.datastruct.interfaces;

/**
 * @author yhl
 * @date 2019/10/10
 */
public interface MlzjCollections<T> extends MlzjAggregate<T>{

    /**
     * 增加元素
     * @param element 元素
     * @return 自己
     */
    MlzjCollections<T> add(T element);

    /**
     * 线程安全的添加方法
     * @param element 加入的新元素
     * @return 本身
     */
    MlzjCollections<T> addSafe(T element);





    /**
     * 获取长度
     * @return 长度
     */
    int size();

}
