package com.mlzj.commontest.demo.datastruct.graph.auxiliary;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author yhl
 * @date 2019/11/05
 * 边对象
 */
@Data
public class Edge<T> implements Serializable {
    private static final long serialVersionUID = -813065270056388873L;
    /**
     * 起点
     */
    private Vertex<T> vertexStart;

    /**
     * 终点
     */
    private Vertex<T> vertexEnd;

    /**
     * 开始节点的下一条边
     */
    private Edge<T> startLink;

    /**
     * 终点的下一条边
     */
    private Edge<T> endLink;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 获取边上的另一个顶点
     *
     * @param edge   边
     * @param vertex 顶点
     * @return 另一端的顶点
     */
    public Vertex<T> getEdgeNextVertex(Vertex<T> vertex) {
        if (Objects.equals(this.getVertexStart(), vertex)) {
            return this.getVertexEnd();
        }
        return this.getVertexStart();
    }




    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(vertexStart, edge.vertexStart) &&
                Objects.equals(vertexEnd, edge.vertexEnd) &&
                Objects.equals(weight, edge.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexStart.getData(), vertexEnd.getData(), weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertexStart=" + vertexStart.getData() +
                ", vertexEnd=" + vertexEnd.getData() +
                '}';
    }
}
