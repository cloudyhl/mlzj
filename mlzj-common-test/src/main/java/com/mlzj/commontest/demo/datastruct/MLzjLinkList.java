package com.mlzj.commontest.demo.datastruct;

import com.mlzj.commontest.demo.datastruct.abstracts.AbstractMlzjList;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjCollections;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjIteration;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjList;
import com.mlzj.commontest.demo.datastruct.iterators.MlzjLinkListIterator;
import com.mlzj.commontest.demo.datastruct.model.MlzjNode;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yhl
 * @date 2019/10/11
 */
@SuppressWarnings("all")
public class MLzjLinkList<T> extends AbstractMlzjList<T> implements MlzjList<T> {

    private MlzjNode<T> head;

    private MlzjNode<T> last;

    private AtomicInteger index = new AtomicInteger(0);

    private int size;

    public MLzjLinkList() {
        this.head = new MlzjNode<>();
    }

    private void init(T element) {
        head.setValue(element);
        head.setNext(new MlzjNode<>());
        this.last = head;
        this.size = index.incrementAndGet();
    }

    @Override
    public MlzjIteration<T> createIteration() {
        return new MlzjLinkListIterator<>(head);
    }

    @Override
    public T get(int index) {
        this.checkIndex(index);
        MlzjNode<T> mlzjNode = head;
        for (int start = 0; start < size; start++) {
            if (start == index) {
                return mlzjNode.getValue();
            }
            mlzjNode = mlzjNode.getNext();
        }
        return null;
    }

    @Override
    public void set(int index, T element) {
        this.checkIndex(index);
        MlzjNode<T> mlzjNode = head;
        for (int start = 0; start < size; start++) {
            if (start == index) {
                mlzjNode.setValue(element);
            }
            mlzjNode = mlzjNode.getNext();
        }
    }

    @Override
    public void remove(T element) {
        if (this.size == 1) {
            head = new MlzjNode<>();
        }
        MlzjIteration<T> iteration = this.createIteration();
        MlzjNode<T> mlzjNode = head;
        while (mlzjNode.getValue() != null) {
            if (Objects.equals(mlzjNode.getValue(), element)) {
                if (mlzjNode.getPrevious() != null) {
                    mlzjNode.getPrevious().setNext(mlzjNode.getNext());
                }
                if (mlzjNode.getNext().getValue() != null){
                    mlzjNode.getNext().setPrevious(mlzjNode.getPrevious());
                }
                if (head == mlzjNode) {
                    head = mlzjNode.getNext();
                }
            }
            mlzjNode = mlzjNode.getNext();
        }
    }

    @Override
    public MlzjCollections<T> add(T element) {
        if (head.getValue() == null) {
            init(element);
            return this;
        }
        MlzjNode<T> mlzjNode = new MlzjNode<>();
        mlzjNode.setPrevious(last);
        mlzjNode.setValue(element);
        mlzjNode.setNext(new MlzjNode<>());
        last.setNext(mlzjNode);
        last = mlzjNode;
        this.size = index.incrementAndGet();
        return this;
    }

    @Override
    public synchronized MlzjCollections<T> addSafe(T element) {
        return this.add(element);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 检验传入的index是否合法
     *
     * @param index 下标索引
     */
    private void checkIndex(int index) {
        if (index >= this.index.intValue() || index < 0) {
            throw new IndexOutOfBoundsException("index is bigger than this array's length or smaller than zero");
        }
    }
}
