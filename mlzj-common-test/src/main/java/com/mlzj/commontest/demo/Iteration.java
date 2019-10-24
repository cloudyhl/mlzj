package com.mlzj.commontest.demo;

/**
 * @author yhl
 * @date 2019/9/20
 */
public interface Iteration<T> {

    /**
     * 是否存在下一个
     * @return 是否存在
     */
    boolean hasNext();

    /**
     * 返回下一个元素
     * @return 元素
     */
    T next();
}
