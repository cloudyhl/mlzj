package com.mlzj.commontest.demo.datastruct.graph.auxiliary;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yhl
 * @date 2019/11/05
 * 顶点对象
 */
@Data
public class Vertex<T> implements Serializable {
    private static final long serialVersionUID = 5429263978385628582L;
    /**
     * 具体数据
     */
    private T data;

    /**
     * 第一条边
     */
    private Edge<T> firstEdge;

    /**
     * 返回这个顶点所有的边
     *
     * @param vertex 顶点
     * @return 边集
     */
    public List<Edge<T>> getEdgesByVertex() {
        List<Edge<T>> vertexEdges = new ArrayList<>();
        this.vertexHasNextEdge(this, this.getFirstEdge(), vertexEdges);
        return vertexEdges;
    }

    /**
     * 判断顶点关联的边
     *
     * @param vertex      顶点
     * @param edge        边
     * @param vertexEdges 将边数据填充至此集合
     */
    private void vertexHasNextEdge(Vertex<T> vertex, Edge<T> edge, List<Edge<T>> vertexEdges) {
        if (edge != null && Objects.equals(vertex, edge.getVertexStart())) {
            vertexEdges.add(edge);
            this.vertexHasNextEdge(edge.getVertexStart(), edge.getStartLink(), vertexEdges);
        } else if (edge != null && Objects.equals(vertex, edge.getVertexEnd())) {
            vertexEdges.add(edge);
            this.vertexHasNextEdge(edge.getVertexEnd(), edge.getEndLink(), vertexEdges);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(data, vertex.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "data=" + data +
                '}';
    }
}
