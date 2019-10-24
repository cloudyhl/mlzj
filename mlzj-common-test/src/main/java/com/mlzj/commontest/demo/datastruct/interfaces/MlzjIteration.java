package com.mlzj.commontest.demo.datastruct.interfaces;


/**
 * @author yhl
 * @date 2019/10/10
 */
public interface MlzjIteration<T> {

    /**
     * 是否存在下一个元素
     * @return 是否
     */
    boolean hasNext();

    /**
     * 返回当前数据
     * @return 当前数据
     */
    T getCurrentData();

}
