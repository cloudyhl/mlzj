package com.mlzj.commontest.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhl
 * @date 2019/9/20
 */
public class ListAggregate<T> implements Aggregate {

    private List<T> list = new ArrayList<>();



    Integer size(){
        return list.size();
    }

    T getIndex(Integer index){
        return list.get(index);
    }

    public ListAggregate<T> addItem(T obj){
        list.add(obj);
        return this;
    }

    @Override
    public Iteration<T> createIterator() {
        return new ListIteration<>(this);
    }
}
