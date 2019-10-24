package com.mlzj.commontest.demo;

/**
 * @author yhl
 * @date 2019/9/20
 */
public interface Aggregate<T> {


    /**
     * 创建迭代器
     * @return 迭代器
     */
    Iteration<T> createIterator();

}
