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
public class FloydMinRouteResp<T> {
    /**
     * 最段路劲集合,记录所有点到其他各点的距离
     */
    Map<Vertex<T>, Map<Vertex<T>, Integer>> floydMinMap;

    /**
     * 记录求出最短路径所对应的的边集
     */
    Map<Vertex<T>, Map<Vertex<T>, List<Edge<T>>>> floydMinEdgesMap;
}
