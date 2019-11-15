package com.mlzj.commontest.demo.datastruct.domain;

import com.mlzj.commontest.demo.datastruct.graph.auxiliary.ArcEdge;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.ArcVertex;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author yhl
 * @date 2019/11/13
 */
@Data
public class DirectedDijkstraMinRouteResp<T> {
    /**
     * 到各个顶点的最短距离
     */
    private Map<ArcVertex<T>, Integer> minRouteMap;


    /**
     * 最短路径的边集
     */
    private Map<ArcVertex<T>, List<ArcEdge<T>>> minEdges;

}
