package com.mlzj.commontest.demo.datastruct.graph;

import com.mlzj.commontest.demo.datastruct.domain.DirectedDijkstraMinRouteResp;
import com.mlzj.commontest.demo.datastruct.domain.DirectedFloydMinRouteResp;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.ArcEdge;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.ArcVertex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

/**
 * 有项图
 *
 * @author yhl
 * @date 2019/11/12
 */
@SuppressWarnings("all")
public class DirectedGraph<T> {

    /**
     * 顶点集合
     */
    private List<ArcVertex<T>> arcVertexs = new ArrayList<>();

    /**
     * 边集合
     */
    private List<ArcEdge<T>> arcEdges = new ArrayList<>();

    /**
     * 获取所有的顶点集合
     *
     * @return 所有的顶点
     */
    public List<ArcVertex<T>> getVertexs() {
        return this.arcVertexs;
    }

    /**
     * 获取所有的边集合
     *
     * @return 所有边
     */
    public List<ArcEdge<T>> getEdges() {
        return this.arcEdges;
    }


    /**
     * 增加一条边
     *
     * @param start  起点
     * @param end    终点
     * @param weight 权重
     */
    public void addArcEdge(ArcVertex<T> start, ArcVertex<T> end, Integer weight) {
        ArcEdge<T> tArcEdge = this.checExistEdge(start, end);
        if (Objects.isNull(tArcEdge)) {
            ArcEdge<T> newEdge = this.buildEdge(start, end, weight);
            this.arcEdges.add(newEdge);
            if (Objects.isNull(start.getFirstOut())) {
                start.setFirstOut(newEdge);
            }
        }
    }

    /**
     * 增加一条边
     *
     * @param start  起点
     * @param end    终点
     * @param weight 权重
     */
    public void addArcEdge(ArcVertex<T> start, ArcVertex<T> end) {
        this.addArcEdge(start, end, null);
    }

    /**
     * 返回顶点个数
     *
     * @return 顶点数
     */
    public Integer getVertexSize() {
        return this.arcVertexs.size();
    }

    /**
     * 构建一条边
     *
     * @param start  起点
     * @param end    终点
     * @param weight 权重
     * @return 构建好的边
     */
    private ArcEdge<T> buildEdge(ArcVertex<T> start, ArcVertex<T> end, Integer weight) {
        ArcEdge<T> newEdge = new ArcEdge<>();
        newEdge.setVertexStart(start);
        newEdge.setVertexEnd(end);
        newEdge.setWeight(weight);
        return newEdge;
    }


    /**
     * 新增一个顶点
     *
     * @param data 具体数据
     * @return 新增的顶点
     */
    public ArcVertex<T> addVertex(T data) {
        ArcVertex<T> newVertex = new ArcVertex<>();
        newVertex.setData(data);
        this.arcVertexs.add(newVertex);
        return newVertex;
    }


    /**
     * 获取顶点
     *
     * @param data 具体数据
     * @return 与数据相同的顶点
     */
    public ArcVertex<T> getVertex(T data) {
        for (ArcVertex<T> arcVertex : this.arcVertexs) {
            if (Objects.equals(arcVertex.getData(), data)) {
                return arcVertex;
            }
        }
        return null;
    }

    /**
     * 新增边时检验是否存在点  或边重复
     *
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     */
    private ArcEdge<T> checExistEdge(ArcVertex<T> start, ArcVertex<T> end) {
        for (ArcEdge<T> arcEdge : this.arcEdges) {
            if (Objects.equals(arcEdge.getVertexStart(), start) && Objects.equals(arcEdge.getVertexEnd(), end)) {
                return arcEdge;
            }
        }
        return null;
    }


    /**
     * 构建图
     */
    public void drawGraph() {
        for (ArcVertex<T> arcVertex : this.arcVertexs) {
            this.setNextEdge(arcVertex);
        }
    }

    /**
     * 设置下一条边 顶点设置firstIn firstOut 将入边出边接在后面
     *
     * @param arcVertex 顶点
     */
    private void setNextEdge(ArcVertex<T> arcVertex) {
        for (ArcEdge<T> arcEdge : arcEdges) {
            if (Objects.isNull(arcVertex.getFirstOut()) && Objects.equals(arcEdge.getVertexStart(), arcVertex)) {
                arcVertex.setFirstOut(arcEdge);
                continue;
            }
            if (Objects.isNull(arcVertex.getFirstIn()) && Objects.equals(arcEdge.getVertexEnd(), arcVertex)) {
                arcVertex.setFirstIn(arcEdge);
                continue;
            }
            if (Objects.equals(arcEdge.getVertexStart(), arcVertex) && !Objects.equals(arcVertex.getFirstOut(), arcEdge)) {
                ArcEdge<T> nextNullFirstOut = this.getNextNullFirstOut(arcVertex.getFirstOut());
                nextNullFirstOut.setOutLink(arcEdge);
            }
            if (Objects.equals(arcEdge.getVertexEnd(), arcVertex) && !Objects.equals(arcVertex.getFirstIn(), arcEdge)) {
                ArcEdge<T> nextNullFirstIn = this.getNextNullFirstIn(arcVertex.getFirstIn());
                nextNullFirstIn.setInLink(arcEdge);
            }
        }
    }

    /**
     * 获取下一条入边为空的边
     *
     * @param currenEdge 当前的边
     * @return 下一条入边为空的边
     */
    private ArcEdge<T> getNextNullFirstIn(ArcEdge<T> currenEdge) {
        if (Objects.isNull(currenEdge.getInLink())) {
            return currenEdge;
        }
        return getNextNullFirstIn(currenEdge.getInLink());
    }

    /**
     * 获取下一条出边为空的边
     *
     * @param currenEdge 当前的边
     * @return 下一条出边为空的边
     */
    private ArcEdge<T> getNextNullFirstOut(ArcEdge<T> currenEdge) {
        if (Objects.isNull(currenEdge.getOutLink())) {
            return currenEdge;
        }
        return getNextNullFirstOut(currenEdge.getOutLink());
    }

    /**
     * 删除一条边
     *
     * @param start 边的起点
     * @param end   边的终点
     */
    public void delEdge(ArcVertex<T> start, ArcVertex<T> end, List<ArcVertex<T>> vertices) {
        ArcEdge<T> removeEdge = this.checExistEdge(start, end);
        List<ArcVertex<T>> useVertexs = vertices;
        if (Objects.isNull(removeEdge)) {
            throw new IllegalArgumentException("not exist this edge");
        }
        if (CollectionUtils.isEmpty(vertices)) {
            useVertexs = this.arcVertexs;
            this.arcEdges.remove(removeEdge);
        }

        for (ArcVertex<T> vertex : useVertexs) {
            if (Objects.nonNull(vertex.getFirstIn()) && this.isEqualEdgeForTwoVertex(vertex.getFirstIn(), start, end)) {
                vertex.setFirstIn(removeEdge.getInLink());
                continue;
            }
            if (Objects.nonNull(vertex.getFirstOut()) && this.isEqualEdgeForTwoVertex(vertex.getFirstOut(), start, end)) {
                vertex.setFirstOut(removeEdge.getOutLink());
                continue;
            }
            List<ArcEdge<T>> inEdges = vertex.getInEdges();
            List<ArcEdge<T>> outEdges = vertex.getOutEdges();
            for (ArcEdge<T> inEdge : inEdges) {
                if (this.isEqualEdgeForTwoVertex(inEdge.getInLink(), start, end)) {
                    inEdge.setInLink(removeEdge.getInLink());
                }
            }
            for (ArcEdge<T> outEdge : outEdges) {
                if (this.isEqualEdgeForTwoVertex(outEdge.getOutLink(), start, end)) {
                    outEdge.setOutLink(removeEdge.getOutLink());
                }
            }
        }
    }

    public void delEdge(ArcVertex<T> start, ArcVertex<T> end) {
        this.delEdge(start, end, null);
    }

    /**
     * 判断一条边的起点终点是否与传入的起点终点相同
     *
     * @param arcEdge 边
     * @param start   起点
     * @param end     终点
     * @return 是否与边起点终点相同
     */
    private boolean isEqualEdgeForTwoVertex(ArcEdge<T> arcEdge, ArcVertex<T> start, ArcVertex<T> end) {
        return Objects.nonNull(arcEdge) && Objects.equals(arcEdge.getVertexStart(), start) && Objects.equals(arcEdge.getVertexEnd(), end);
    }

    /**
     * 删除顶点
     *
     * @param vertex 顶点
     */
    public void delVertex(ArcVertex<T> vertex) {
        this.arcVertexs.remove(vertex);
        List<ArcEdge<T>> inEdges = vertex.getInEdges();
        List<ArcEdge<T>> outEdges = vertex.getOutEdges();
        inEdges.forEach(inEdge -> {
            this.delEdge(inEdge.getVertexStart(), inEdge.getVertexEnd());
        });
        outEdges.forEach(outEdge -> {
            this.delEdge(outEdge.getVertexStart(), outEdge.getVertexEnd());
        });

    }


    /**
     * 使用迪杰斯特拉算法求点到各个可达点的最短路径 不可达为null
     *
     * @param start 起点
     * @return 最短路径边集合 及最短路径值
     */
    public DirectedDijkstraMinRouteResp<T> getDijkstraMinRoute(ArcVertex<T> start) {
        DirectedDijkstraMinRouteResp<T> directedDijkstraMinRouteResp = new DirectedDijkstraMinRouteResp<>();
        Map<ArcVertex<T>, Integer> minRouteMap = new HashMap<>(16);
        Set<ArcVertex<T>> usedVertexs = new HashSet<>();
        Map<ArcVertex<T>, List<ArcEdge<T>>> minRouteEdgesMap = new HashMap<>(8);
        this.initMinMap(minRouteMap, minRouteEdgesMap);
        minRouteMap.put(start, 0);
        for (ArcVertex<T> arcVertex : this.arcVertexs) {
            ArcVertex<T> minArcVertex = this.getMinArcVertex(minRouteMap, usedVertexs);
            usedVertexs.add(minArcVertex);
            List<ArcEdge<T>> outEdges = minArcVertex.getOutEdges();
            for (ArcEdge<T> outEdge : outEdges) {
                if (minRouteMap.get(minArcVertex) + outEdge.getWeight() < minRouteMap.get(outEdge.getVertexEnd())) {
                    minRouteMap.put(outEdge.getVertexEnd(), minRouteMap.get(minArcVertex) + outEdge.getWeight());
                    List<ArcEdge<T>> arcEdges = new ArrayList<>(minRouteEdgesMap.get(minArcVertex));
                    arcEdges.add(outEdge);
                    minRouteEdgesMap.put(outEdge.getVertexEnd(), arcEdges);
                }
            }
        }
        minRouteMap.forEach((key, value) -> {
            if (Objects.equals(value, Integer.MAX_VALUE)) {
                minRouteMap.put(key, null);
            }
        });
        directedDijkstraMinRouteResp.setMinRouteMap(minRouteMap);
        directedDijkstraMinRouteResp.setMinEdges(minRouteEdgesMap);
        return directedDijkstraMinRouteResp;
    }

    /**
     * 当前点到
     *
     * @param minRouteMap 最短路径集合
     * @param usedVertexs 已经使用过的点
     * @return 当前点可到最近的点
     */
    private ArcVertex<T> getMinArcVertex(Map<ArcVertex<T>, Integer> minRouteMap, Set<ArcVertex<T>> usedVertexs) {
        ArcVertex<T> minVertex = null;
        Integer minValue = Integer.MAX_VALUE;
        for (Map.Entry<ArcVertex<T>, Integer> entry : minRouteMap.entrySet()) {
            if (usedVertexs.contains(entry.getKey())) {
                continue;
            }
            if (entry.getValue() <= minValue) {
                minVertex = entry.getKey();
                minValue = entry.getValue();
            }
        }
        return minVertex;
    }

    /**
     * 初始化最短路径集合与最短路径边集合
     *
     * @param minRouteMap      最短路径集合
     * @param minRouteEdgesMap 最短路径边集合
     */
    private void initMinMap(Map<ArcVertex<T>, Integer> minRouteMap, Map<ArcVertex<T>, List<ArcEdge<T>>> minRouteEdgesMap) {
        this.arcVertexs.forEach(arcVertex -> {
            minRouteMap.put(arcVertex, Integer.MAX_VALUE);
            List<ArcEdge<T>> edges = new ArrayList<>();
            minRouteEdgesMap.put(arcVertex, edges);
        });
    }

    /**
     * 弗洛伊德算法求最短路径 返回所有的任意两个点的距离
     * 首先初始化所有点到各个点的距离，无法直接到达的则为最大值
     * 然后遍历所有的顶点,每次加入一个新的顶点，允许该点作为中转点,然后判断经过中转点到达目标点和直接到达目标点的大小,小于则更新最短路径集合
     * 本质上与迪杰斯特拉算法相似,均为从一个点慢慢扩散找相邻的点之间的最短路径
     */
    public DirectedFloydMinRouteResp floydMinRoute() {
        DirectedFloydMinRouteResp<T> floydMinRouteResp = new DirectedFloydMinRouteResp<>();
        Map<ArcVertex<T>, Map<ArcVertex<T>, Integer>> floydMinMap = new HashMap<>(16);
        Map<ArcVertex<T>, Map<ArcVertex<T>, List<ArcEdge<T>>>> floydMinEdgesMap = new HashMap<>(16);
        this.initfloydMap(floydMinMap, floydMinEdgesMap);
        for (ArcVertex<T> joinVertex : this.arcVertexs) {
            for (Map.Entry<ArcVertex<T>, Map<ArcVertex<T>, Integer>> entry : floydMinMap.entrySet()) {
                //currentVertex当前正在遍历的点
                ArcVertex<T> currentVertex = entry.getKey();
                Map<ArcVertex<T>, Integer> vertexMap = entry.getValue();
                if (Objects.equals(currentVertex, joinVertex)) {
                    continue;
                }
                for (Map.Entry<ArcVertex<T>, Integer> target : vertexMap.entrySet()) {
                    //targetVertex 目标点 由当前点到目标点 求之间距离 算法为当前点->中转点距离+中转点->目标点距离 < 当前点->目标点距离
                    ArcVertex<T> targetVertex = target.getKey();
                    Integer weight = target.getValue();
                    //由于Inter.MAX_VALUE 想加之后会成为负数,所以当有最大值时无须比较，原有的值一定小于想加后的值
                    if (Objects.equals(vertexMap.get(joinVertex), Integer.MAX_VALUE) || Objects.equals(floydMinMap.get(joinVertex).get(targetVertex), Integer.MAX_VALUE)) {
                        continue;
                    }
                    if (vertexMap.get(joinVertex) + floydMinMap.get(joinVertex).get(targetVertex) < vertexMap.get(targetVertex)) {
                        vertexMap.put(targetVertex, vertexMap.get(joinVertex) + floydMinMap.get(joinVertex).get(targetVertex));
                        List<ArcEdge<T>> tempList = new ArrayList<>(floydMinEdgesMap.get(joinVertex).get(targetVertex));
                        tempList.addAll(new ArrayList<>(floydMinEdgesMap.get(currentVertex).get(joinVertex)));
                        floydMinEdgesMap.get(currentVertex).put(targetVertex, tempList);
                    }
                }
            }
        }
        floydMinRouteResp.setFloydMinEdgesMap(floydMinEdgesMap);
        floydMinRouteResp.setFloydMinMap(floydMinMap);
        return floydMinRouteResp;
    }

    /**
     * 初始化folydMap 出事化两个map 一个用于存储最短路径的值,另一个用于存储 到达目标点最短路径边集
     */
    private void initfloydMap(Map<ArcVertex<T>, Map<ArcVertex<T>, Integer>> floydMinMap, Map<ArcVertex<T>, Map<ArcVertex<T>, List<ArcEdge<T>>>> floydMinEdgesMap) {
        for (ArcVertex<T> vertex : this.arcVertexs) {
            Map<ArcVertex<T>, Integer> routeMap = new HashMap<>(16);
            Map<ArcVertex<T>, List<ArcEdge<T>>> minEdgeMap = new HashMap<>(16);
            for (ArcVertex<T> targetVertex : this.arcVertexs) {
                routeMap.put(targetVertex, Integer.MAX_VALUE);
                minEdgeMap.put(targetVertex, new ArrayList<>());
            }
            routeMap.put(vertex, 0);
            List<ArcEdge<T>> edgesByVertex = vertex.getOutEdges();
            for (ArcEdge<T> edgeByVertex : edgesByVertex) {
                ArcVertex<T> edgeNextVertex = edgeByVertex.getVertexEnd();
                routeMap.put(edgeNextVertex, edgeByVertex.getWeight());
                minEdgeMap.get(edgeNextVertex).add(edgeByVertex);
            }
            floydMinMap.put(vertex, routeMap);
            floydMinEdgesMap.put(vertex, minEdgeMap);
        }
    }

    /**
     * 获取拓扑排序
     *
     * @return 拓扑排序点集
     */
    public List<ArcVertex<T>> topologySort() {
        List<ArcVertex<T>> resultList = new ArrayList<>();
        List<ArcVertex<T>> vertices = new ArrayList<>();
        this.arcVertexs.forEach(arcVertex -> {
            vertices.add(SerializationUtils.clone(arcVertex));
        });
        this.getTopologyVertex(resultList, vertices);
        return resultList;
    }


    /**
     * 获取入度为0的点 删除这个点 依次找入度为0的点
     *
     * @param resultList 结果集
     * @param vertices   所有的点集
     */
    private void getTopologyVertex(List<ArcVertex<T>> resultList, List<ArcVertex<T>> vertices) {
        for (ArcVertex<T> vertex : vertices) {
            if (resultList.contains(vertex)) {
                continue;
            }
            if (CollectionUtils.isEmpty(vertex.getInEdges())) {
                resultList.add(vertex);
                List<ArcEdge<T>> outEdges = vertex.getOutEdges();
                outEdges.forEach(outEdge -> this.delEdge(outEdge.getVertexStart(), outEdge.getVertexEnd(), vertices));
                this.getTopologyVertex(resultList, vertices);
            }
        }
    }

    /**
     * 求关键路径边集
     *
     * @return 关键路径的边集
     */
    public List<ArcEdge<T>> cruxRoute() {
        List<ArcEdge<T>> cruxEdges = new ArrayList<>();
        List<ArcVertex<T>> cruxVertexs = new ArrayList<>();
        Map<ArcVertex<T>, Integer> minBeginTime = new HashMap<>(16);
        Map<ArcVertex<T>, Integer> maxBeginTime = new HashMap<>(16);
        ArcVertex<T> start = this.getCruxStart();
        minBeginTime.put(start, 0);
        this.getMinBeginTime(start, minBeginTime);
        ArcVertex<T> end = this.getCruxEnd();
        maxBeginTime.put(end, minBeginTime.get(end));
        this.getMaxBeginTime(end, maxBeginTime, minBeginTime);
        minBeginTime.forEach(((arcVertex, time) -> {
            if (Objects.equals(maxBeginTime.get(arcVertex), time)) {
                cruxVertexs.add(arcVertex);
            }
        }));
        this.buildCruxEdges(cruxEdges, start, end, cruxVertexs);
        return cruxEdges;
    }

    /**
     * 获取关键路径开始节点
     *
     * @return 开始节点
     */
    private ArcVertex<T> getCruxStart() {
        for (ArcVertex<T> arcVertex : this.arcVertexs) {
            if (CollectionUtils.isEmpty(arcVertex.getInEdges())) {
                return arcVertex;
            }
        }
        return null;
    }

    /**
     * 通过顶点 构建 关键路径访问过的边
     *
     * @param cruxEdges 关键路径边集
     * @param start     当前节点
     * @param end       终止节点
     * @param vertices  关键路径访问过的点集合
     */
    private void buildCruxEdges(List<ArcEdge<T>> cruxEdges, ArcVertex<T> current, ArcVertex<T> end, List<ArcVertex<T>> vertices) {
        if (Objects.equals(current, end)) {
            return;
        }
        List<ArcEdge<T>> outEdges = current.getOutEdges();
        for (ArcEdge<T> outEdge : outEdges) {
            if (vertices.contains(outEdge.getVertexEnd())) {
                cruxEdges.add(outEdge);
                this.buildCruxEdges(cruxEdges, outEdge.getVertexEnd(), end, vertices);
            }
        }
    }

    /**
     * 获取关键路径终止节点节点
     *
     * @return 终止节点
     */
    private ArcVertex<T> getCruxEnd() {
        for (ArcVertex<T> arcVertex : this.arcVertexs) {
            if (CollectionUtils.isEmpty(arcVertex.getOutEdges())) {
                return arcVertex;
            }
        }
        return null;
    }

    /**
     * 获取活动最早开始时间
     *
     * @param current      当前的点
     * @param minBeginTime 最小开始时间map
     */
    private void getMinBeginTime(ArcVertex<T> current, Map<ArcVertex<T>, Integer> minBeginTime) {
        List<ArcEdge<T>> outEdges = current.getOutEdges();
        for (ArcEdge<T> outEdge : outEdges) {
            Integer time = minBeginTime.get(outEdge.getVertexEnd());
            if (Objects.isNull(time)) {
                minBeginTime.put(outEdge.getVertexEnd(), minBeginTime.get(current) + outEdge.getWeight());
            } else {
                if (minBeginTime.get(current) + outEdge.getWeight() > minBeginTime.get(outEdge.getVertexEnd())) {
                    minBeginTime.put(outEdge.getVertexEnd(), (minBeginTime.get(current) + outEdge.getWeight()));
                }
            }
            getMinBeginTime(outEdge.getVertexEnd(), minBeginTime);
        }
    }

    /**
     * 获取活动最晚开始时间
     *
     * @param current      当前的点
     * @param maxBeginTime 最晚开始时间集合
     * @param minBeginTime 最早开始时间集合
     */
    private void getMaxBeginTime(ArcVertex<T> current, Map<ArcVertex<T>, Integer> maxBeginTime, Map<ArcVertex<T>, Integer> minBeginTime) {
        List<ArcEdge<T>> inEdges = current.getInEdges();
        for (ArcEdge<T> inEdge : inEdges) {
            maxBeginTime.put(inEdge.getVertexStart(), minBeginTime.get(current) - inEdge.getWeight());
            this.getMaxBeginTime(inEdge.getVertexStart(), maxBeginTime, minBeginTime);
        }
    }

    /**
     * 深度遍历
     *
     * @param startVertex 起点
     * @return 从起点开始遍历可达的所有顶点  使用深度有限遍历
     */
    public List<ArcVertex<T>> dfsErgodic(ArcVertex<T> startVertex) {
        List<ArcVertex<T>> resultList = new ArrayList<>();
        resultList.add(startVertex);
        this.hasNextVertex(startVertex, resultList);
        return resultList;
    }

    /**
     * 查看当前节点是否还可以向后遍历如果可以就继续向下遍历
     *
     * @param current    当前节点
     * @param resultList 结果集
     */
    private void hasNextVertex(ArcVertex<T> current, List<ArcVertex<T>> resultList) {
        List<ArcEdge<T>> outEdges = current.getOutEdges();
        for (ArcEdge<T> outEdge : outEdges) {
            ArcVertex<T> vertexEnd = outEdge.getVertexEnd();
            if (!resultList.contains(vertexEnd)) {
                resultList.add(vertexEnd);
                this.hasNextVertex(vertexEnd, resultList);
            }
        }
    }

    /**
     * 广度遍历
     * @param startVertex 开始节点
     * @return 广度遍历的所有可达点
     */
    public List<ArcVertex<T>> bfsErgodic(ArcVertex<T> startVertex) {
        List<ArcVertex<T>> resultList = new ArrayList<>();
        Queue<ArcVertex<T>> visitQueue = new ArrayDeque<>();
        visitQueue.add(startVertex);
        resultList.add(startVertex);
        while (CollectionUtils.isNotEmpty(visitQueue)) {
            ArcVertex<T> head = visitQueue.poll();
            List<ArcEdge<T>> outEdges = head.getOutEdges();
            for (ArcEdge<T> outEdge : outEdges) {
                if (!resultList.contains(outEdge.getVertexEnd())) {
                    visitQueue.add(outEdge.getVertexEnd());
                    resultList.add(outEdge.getVertexEnd());
                }
            }
        }
        return resultList;
    }

}
