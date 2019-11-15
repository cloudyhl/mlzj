package com.mlzj.commontest.demo.datastruct.graph.auxiliary;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 有向图 弧
 *
 * @author yhl
 * @date 2019/11/12
 */
@Data
public class ArcEdge<T> implements Serializable {

    private static final long serialVersionUID = -2260486021945210979L;
    /**
     * 起点
     */
    private ArcVertex<T> vertexStart;

    /**
     * 终点
     */
    private ArcVertex<T> vertexEnd;

    /**
     * 开始节点的下一条边
     */
    private ArcEdge<T> inLink;

    /**
     * 终点的下一条边
     */
    private ArcEdge<T> outLink;

    /**
     * 权重
     */
    private Integer weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArcEdge<?> arcEdge = (ArcEdge<?>) o;
        return Objects.equals(vertexStart, arcEdge.vertexStart) &&
                Objects.equals(vertexEnd, arcEdge.vertexEnd) &&
                Objects.equals(weight, arcEdge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexStart.getData(), vertexEnd.getData(), weight);
    }

    @Override
    public String toString() {
        return "ArcEdge{" +
                "vertexStart=" + vertexStart.getData() +
                ", vertexEnd=" + vertexEnd.getData() +
                '}';
    }
}
