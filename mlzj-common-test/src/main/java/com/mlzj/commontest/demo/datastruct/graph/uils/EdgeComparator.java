package com.mlzj.commontest.demo.datastruct.graph.uils;

import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Edge;

import java.util.Comparator;

/**
 * @author yhl
 * @date 2019/11/08
 */
public class EdgeComparator<T> implements Comparator<Edge<T>> {
    @Override
    public int compare(Edge<T> edge1, Edge<T> edge2) {
        if (edge1.getWeight() > edge2.getWeight()){
            return 1;
        } else if (edge1.getWeight().equals(edge2.getWeight())){
            return 0;
        }
        return -1;
    }
}