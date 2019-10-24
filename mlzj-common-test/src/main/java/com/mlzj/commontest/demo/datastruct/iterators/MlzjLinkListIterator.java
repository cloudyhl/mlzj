package com.mlzj.commontest.demo.datastruct.iterators;

import com.mlzj.commontest.demo.datastruct.interfaces.MlzjIteration;
import com.mlzj.commontest.demo.datastruct.model.MlzjNode;

/**
 * @author yhl
 * @date 2019/9/11
 */
public class MlzjLinkListIterator<T> implements MlzjIteration<T> {

    private MlzjNode<T> currentData;

    public MlzjLinkListIterator(MlzjNode<T> head){
        currentData = head;
    }

    @Override
    public boolean hasNext() {
        return currentData.getValue() != null;
    }

    @Override
    public T getCurrentData() {
        T currentValue = currentData.getValue();
        currentData = currentData.getNext();
        return currentValue;
    }
}
