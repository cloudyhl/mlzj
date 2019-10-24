package com.mlzj.commontest.demo.datastruct.iterators;

import com.mlzj.commontest.demo.datastruct.MlzjArrayList;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjIteration;

/**
 * @author yhl
 * @date 2019/10/10
 */
public class MlzjArrayListIterator<T> implements MlzjIteration<T> {


    private MlzjArrayList<T> mlzjArrayList;

    /**
     * 当前遍历的节点
     */
    private int current = 0;


    public MlzjArrayListIterator(MlzjArrayList<T> mlzjArrayList){
        this.mlzjArrayList = mlzjArrayList;
    }

    @Override
    public boolean hasNext() {
        return current < mlzjArrayList.size();
    }

    @Override
    public T getCurrentData() {
        return mlzjArrayList.get(current++);
    }
}
