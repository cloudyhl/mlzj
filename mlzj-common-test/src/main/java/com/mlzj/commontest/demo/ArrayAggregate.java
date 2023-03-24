package com.mlzj.commontest.demo;

/**
 * @author yhl
 * @date 2019/9/20
 */
public class ArrayAggregate<T> implements Aggregate<T>{

    private final T [] array;

    private int current = 0;

    @Override
    public Iteration<T> createIterator() {
        return new ArrayIteration<>(this);
    }

    public ArrayAggregate(T[] array){
        this.array = array;
    }

    int size(){
        return array.length;
    }

    T getIndex(int index){
        return array[index];
    }

    public ArrayAggregate<T> addItem(T obj){
        if (current+1 >array.length){
            throw new IndexOutOfBoundsException("arrayAggregate size must < array.length");
        }
        array[current++] = obj;
        return this;
    }
}
