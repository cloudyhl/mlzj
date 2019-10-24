package com.mlzj.commontest.demo.datastruct.interfaces;

/**
 * @author yhl
 * @date 2019/10/10
 */
public interface MlzjAggregate<T> {

    /**
     * 创建迭代器
     * @return 迭代器
     */
    MlzjIteration<T> createIteration();
}
