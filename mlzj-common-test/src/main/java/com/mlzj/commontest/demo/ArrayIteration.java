package com.mlzj.commontest.demo;

/**
 * @author yhl
 * @date 2019/9/20
 */
public class ArrayIteration<T> implements Iteration<T> {

    private ArrayAggregate<T> arrayAggregate;

    private int current = 0;

    ArrayIteration(ArrayAggregate<T> arrayAggregate) {
        this.arrayAggregate = arrayAggregate;
    }

    @Override
    public boolean hasNext() {
        return current < arrayAggregate.size() && arrayAggregate.getIndex(current) != null;
    }

    @Override
    public T next() {
        return arrayAggregate.getIndex(current++);
    }
}
