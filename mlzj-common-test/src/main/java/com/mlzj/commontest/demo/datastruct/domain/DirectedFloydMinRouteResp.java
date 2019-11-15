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
public class DirectedFloydMinRouteResp<T> {
    /**
     * 最段路劲集合,记录所有点到其他各点的距离
     */
    Map<ArcVertex<T>, Map<ArcVertex<T>, Integer>> floydMinMap;

    /**
     * 记录求出最短路径所对应的的边集
     */
    Map<ArcVertex<T>, Map<ArcVertex<T>, List<ArcEdge<T>>>> floydMinEdgesMap;
}
