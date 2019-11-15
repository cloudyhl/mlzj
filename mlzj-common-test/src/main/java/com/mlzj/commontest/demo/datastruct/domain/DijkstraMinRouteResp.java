package com.mlzj.commontest.demo.datastruct.domain;

import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Edge;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Vertex;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author yhl
 * @date 2019/11/12
 */
@Data
public class DijkstraMinRouteResp<T> {

    /**
     * 到各个顶点的最短距离
     */
    private Map<Vertex<T>, Integer> minRouteMap;


    /**
     * 最短路径的边集
     */
    private Map<Vertex<T>, List<Edge<T>>> minEdges;

}
