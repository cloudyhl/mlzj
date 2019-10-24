package com.mlzj.commontest.demo.datastruct.abstracts;

import com.mlzj.commontest.demo.datastruct.interfaces.MlzjIteration;

/**
 * @author yhl
 * @date 2019/10/10
 */
@SuppressWarnings("all")
public abstract class AbstractMlzjList<T> {

    /**
     * 获取迭代器
     *
     * @return 迭代器
     */
    abstract public MlzjIteration<T> createIteration();

    @Override
    public String toString() {
        MlzjIteration<T> it = createIteration();
        if (!it.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            T e = it.getCurrentData();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }

}
