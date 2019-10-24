package com.mlzj.commontest.demo;

/**
 * @author yhl
 * @date 2019/9/20
 */
public class ListIteration<T> implements Iteration<T> {

    private ListAggregate<T> listAggregate;

    private int current = 0;

    ListIteration(ListAggregate<T> listAggregate){
        this.listAggregate = listAggregate;
    }

    @Override
    public boolean hasNext() {
        return current < listAggregate.size();
    }

    @Override
    public T next() {
        return listAggregate.getIndex(current++);
    }
}
