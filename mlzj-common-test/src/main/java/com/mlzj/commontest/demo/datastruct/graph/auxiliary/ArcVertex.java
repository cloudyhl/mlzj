package com.mlzj.commontest.demo.datastruct.graph.auxiliary;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yhl
 * @date 2019/11/12
 */
@Data
public class ArcVertex<T> implements Serializable {
    private static final long serialVersionUID = -7376791126822147718L;
    /**
     * 具体数据
     */
    private T data;

    /**
     * 第一条入边
     */
    private ArcEdge<T> firstIn;

    /**
     * 第一条出边
     */
    private ArcEdge<T> firstOut;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;

        }
        ArcVertex<?> arcVertex = (ArcVertex<?>) o;
        return Objects.equals(data, arcVertex.data);
    }

    /**
     * 获取所有的入边
     * @return 所有的入边
     */
    public List<ArcEdge<T>> getInEdges() {
        List<ArcEdge<T>> arcEdges = new ArrayList<>();
        this.hasNextInEdge(this.firstIn, arcEdges);
        return arcEdges;
    }

    /**
     * 是否还存在下一条入边,有则加入这条入边
     * @param currentEdge 当前的边
     * @param arcEdges 边集合
     */
    private void hasNextInEdge(ArcEdge<T> currentEdge, List<ArcEdge<T>> arcEdges) {
        if (Objects.nonNull(currentEdge)) {
            arcEdges.add(currentEdge);
            hasNextInEdge(currentEdge.getInLink(), arcEdges);
        }
    }

    /**
     * 获取所有的出边
     * @return 所有的出边
     */
    public List<ArcEdge<T>> getOutEdges() {
        List<ArcEdge<T>> arcEdges = new ArrayList<>();
        this.hasNextOutEdge(this.firstOut, arcEdges);
        return arcEdges;
    }

    /**
     * 是否还存在下一条出边,有则加入这条出边
     * @param currentEdge 当前边
     * @param arcEdges 需要返回的集合
     */
    private void hasNextOutEdge(ArcEdge<T> currentEdge, List<ArcEdge<T>> arcEdges) {
        if (Objects.nonNull(currentEdge)) {
            arcEdges.add(currentEdge);
            hasNextOutEdge(currentEdge.getOutLink(), arcEdges);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "ArcVertex{" +
                "data=" + data +
                '}';
    }
}
