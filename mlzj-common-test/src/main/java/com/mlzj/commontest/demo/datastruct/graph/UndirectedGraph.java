package com.mlzj.commontest.demo.datastruct.graph;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mlzj.commontest.demo.datastruct.domain.DijkstraMinRouteResp;
import com.mlzj.commontest.demo.datastruct.domain.FloydMinRouteResp;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Edge;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Vertex;
import com.mlzj.commontest.demo.datastruct.graph.uils.EdgeComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 无向图
 *
 * @author yhl
 * @date 2019/11/6
 */
@SuppressWarnings("all")
public class UndirectedGraph<T> {

    /**
     * 点的开始节点标志
     */
    private static final String PREFIX_START = "START";

    /**
     * 点的末尾节点标志
     */
    private static final String PREFIX_END = "END";


    /**
     * 顶点集合
     */
    private List<Vertex<T>> vertexs = new ArrayList<>();

    /**
     * 边集合
     */
    private List<Edge<T>> edges = new ArrayList<>();

    /**
     * 添加一个顶点
     *
     * @param data 数据
     */
    public Vertex<T> addVertex(T data) {
        Vertex<T> vertex = new Vertex<>();
        vertex.setData(data);
        this.vertexs.add(vertex);
        return vertex;
    }

    /**
     * 给图新增一条边
     *
     * @param start 开始节点
     * @param end   结束节点
     */
    public void addEdge(Vertex<T> start, Vertex<T> end) {
        this.addEdge(start, end, null);
    }

    /**
     * 给图新增一条边
     *
     * @param vertex1 开始节点
     * @param vertex2 结束节点
     * @param weight  权重
     */
    public void addEdge(Vertex<T> vertex1, Vertex<T> vertex2, Integer weight) {
        this.checkOnAddEdge(vertex1, vertex2);
        Edge<T> newEdge = this.buildEdge(vertex1, vertex2, weight);
        if (vertex1.getFirstEdge() == null) {
            vertex1.setFirstEdge(newEdge);
        }
    }

    /**
     * 构建图 多重邻接表方式构建图 此方法需要按顺序增加边
     */
    public void buildGraph() {
        for (Edge<T> edge : vertexs.stream().map(Vertex::getFirstEdge).collect(Collectors.toList())) {
            this.hasNextEdge(this.edges, edge, PREFIX_START);
        }
    }

    /**
     * 节点是否还有下一条边
     *
     * @param edges       边集
     * @param currentEdge 当前的边
     * @param prefix      前缀 是与前节点相等还是与后节点相等
     */
    private void hasNextEdge(List<Edge<T>> edges, Edge<T> currentEdge, String prefix) {
        for (Edge<T> edge : edges) {
            if (Objects.equals(edge, currentEdge)) {
                continue;
            }
            if (StringUtils.equals(prefix, PREFIX_START) && (Objects.equals(currentEdge.getVertexStart(), edge.getVertexStart()) || Objects.equals(currentEdge.getVertexStart(), edge.getVertexEnd()))) {
                currentEdge.setStartLink(edge);
                List<Edge<T>> temp = new ArrayList<>(edges);
                temp.remove(currentEdge);
                if (Objects.equals(currentEdge.getVertexStart(), edge.getVertexStart())) {
                    hasNextEdge(temp, edge, PREFIX_START);
                    break;
                } else {
                    hasNextEdge(temp, edge, PREFIX_END);
                    break;
                }

            }
            if (StringUtils.equals(prefix, PREFIX_END) && (Objects.equals(currentEdge.getVertexEnd(), edge.getVertexStart()) || Objects.equals(currentEdge.getVertexEnd(), edge.getVertexEnd()))) {
                currentEdge.setEndLink(edge);
                List<Edge<T>> temp = new ArrayList<>(edges);
                temp.remove(currentEdge);
                if (Objects.equals(currentEdge.getVertexEnd(), edge.getVertexStart())) {
                    hasNextEdge(temp, edge, PREFIX_START);
                    break;
                } else {
                    hasNextEdge(temp, edge, PREFIX_END);
                    break;
                }
            }
        }
    }

    /**
     * 返回顶点个数
     *
     * @return 顶点个数
     */
    public Integer vertexSize() {
        return this.vertexs.size();
    }


    /**
     * 删除边,需要按照添加时的顺序传入节点 思路  由于无向图判断比较麻烦 需要查看边的开始结束点的后继 删除类似于单链表的删除节点
     * 第一条边需要特殊处理 由于没有一条边的后继会是点的第一条边 所以将第一条边提前处理
     *
     * @param start 开始点
     * @param end   结束点
     */
    public synchronized void delEdge(Vertex<T> start, Vertex<T> end) {
        boolean exist = false;
        Edge<T> removeEdge = null;
        Edge<T> existEdge = checkExist(start, end);
        if (existEdge != null) {
            removeEdge = existEdge;
            edges.remove(existEdge);
            exist = true;
        }
        if (exist) {
            for (Vertex<T> vertex : vertexs) {
                Edge<T> firstEdge = vertex.getFirstEdge();
                //如果删除的是第一条边,判断该边是否还有下一条边
                if (Objects.equals(firstEdge.getVertexStart(), removeEdge.getVertexStart()) && Objects.equals(firstEdge.getVertexEnd(), removeEdge.getVertexEnd())) {
                    if (Objects.equals(vertex, firstEdge.getVertexStart())) {
                        vertex.setFirstEdge(firstEdge.getStartLink());
                    } else if (Objects.equals(vertex, firstEdge.getVertexEnd())) {
                        vertex.setFirstEdge(firstEdge.getEndLink());
                    }

                    continue;
                }
                List<Edge<T>> edgesByVertex = vertex.getEdgesByVertex();
                for (Edge<T> current : edgesByVertex) {
                    if ((current.getStartLink() != null && (Objects.equals(current.getStartLink().getVertexStart(), removeEdge.getVertexStart()) && Objects.equals(current.getStartLink().getVertexEnd(), removeEdge.getVertexEnd()))) ||
                            (current.getEndLink() != null && ((Objects.equals(current.getEndLink().getVertexStart(), removeEdge.getVertexStart()) && Objects.equals(current.getEndLink().getVertexEnd(), removeEdge.getVertexEnd()))))) {

                        if (current.getStartLink() != null && (Objects.equals(current.getStartLink().getVertexStart(), removeEdge.getVertexStart()) && Objects.equals(current.getStartLink().getVertexEnd(), removeEdge.getVertexEnd()))) {
                            if (current.getStartLink() != null && Objects.equals(current.getStartLink().getVertexStart(), current.getVertexStart())) {
                                current.setStartLink(removeEdge.getStartLink());
                            }
                            if (current.getStartLink() != null && Objects.equals(current.getStartLink().getVertexEnd(), current.getVertexStart())) {
                                current.setStartLink(removeEdge.getEndLink());
                            }
                        }
                        if (current.getEndLink() != null && (Objects.equals(current.getEndLink().getVertexStart(), removeEdge.getVertexStart()) && Objects.equals(current.getEndLink().getVertexEnd(), removeEdge.getVertexEnd()))) {
                            if (current.getEndLink() != null && Objects.equals(current.getEndLink().getVertexStart(), current.getVertexEnd())) {
                                current.setEndLink(removeEdge.getStartLink());
                            }
                            if (current.getEndLink() != null && Objects.equals(current.getEndLink().getVertexEnd(), current.getVertexEnd())) {
                                current.setEndLink(removeEdge.getEndLink());
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * 删除顶点
     *
     * @param vertex 顶点
     */
    public synchronized void delVertex(Vertex<T> vertex) {
        List<Edge<T>> edgesByVertex = vertex.getEdgesByVertex();
        this.vertexs.remove(vertex);
        edgesByVertex.forEach(edge -> {
            Vertex<T> edgeNextVertex = edge.getEdgeNextVertex(vertex);
            this.delEdge(vertex, edgeNextVertex);
        });
    }


    /**
     * 通过新增边直接构建图
     *
     * @param vertex1 节点1
     * @param vertex2 节点2
     * @param weight  权重
     */
    public void addEdgeBuildGraph(Vertex<T> vertex1, Vertex<T> vertex2, Integer weight) {
        this.checkOnAddEdge(vertex1, vertex2);
        Edge<T> newEdge = this.buildEdge(vertex1, vertex2, weight);
        if (vertex1.getFirstEdge() == null) {
            vertex1.setFirstEdge(newEdge);
        }
        this.drawGraph();
    }

    /**
     * 新增边并重新画图
     *
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     */
    public void addEdgeBuildGraph(Vertex<T> vertex1, Vertex<T> vertex2) {
        this.addEdgeBuildGraph(vertex1, vertex2, null);
    }


    /**
     * 通过已有的边直接构图
     */
    public void drawGraph() {
        List<Vertex<T>> tempVertex = new ArrayList<>();
        edges.forEach(edge -> {
            if (!tempVertex.contains(edge.getVertexStart())) {
                tempVertex.add(edge.getVertexStart());
            }
            if (!tempVertex.contains(edge.getVertexEnd())) {
                tempVertex.add(edge.getVertexEnd());
            }
        });
        for (Vertex<T> vertex : tempVertex) {
            this.vertexSetFirstEdge(vertex);
            if (Objects.equals(vertex, vertex.getFirstEdge().getVertexStart())) {
                hasNextEdge(edges, vertex, PREFIX_START);
            } else {
                hasNextEdge(edges, vertex, PREFIX_END);
            }

        }
    }

    /**
     * 顶点结构默认有第一条边,为边后追加其他的关联边
     *
     * @param edges         边集
     * @param currentVertex 顶点
     * @param prefix        执行前缀 是与边的A顶点相比较还是与边的B顶点相比较
     */
    private void hasNextEdge(List<Edge<T>> edges, Vertex<T> currentVertex, String prefix) {
        Edge<T> firstEdge = currentVertex.getFirstEdge();
        this.hasNextEdge(edges, firstEdge, prefix);
    }

    /**
     * 判断当前节点的第一条边是否为空,若为空则在边集里查找,并为其添加第一条边
     *
     * @param currentVertex 顶点
     */
    private void vertexSetFirstEdge(Vertex<T> currentVertex) {
        if (currentVertex.getFirstEdge() == null) {
            for (Edge<T> firstEdge : edges) {
                if (Objects.equals(currentVertex, firstEdge.getVertexStart()) || Objects.equals(currentVertex, firstEdge.getVertexEnd())) {
                    currentVertex.setFirstEdge(firstEdge);
                    return;
                }
            }
        }
    }


    /**
     * 检验是否存在该边,如果存在返回这条边
     *
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     * @return 顶点为 传入顶点对应的边
     */
    private Edge<T> checkExist(Vertex<T> vertex1, Vertex<T> vertex2) {
        for (Edge<T> edge : this.edges) {
            boolean isExist = (Objects.equals(edge.getVertexStart(), vertex1) && Objects.equals(edge.getVertexEnd(), vertex2))
                    || (Objects.equals(edge.getVertexStart(), vertex2) && Objects.equals(edge.getVertexEnd(), vertex1));
            if (isExist) {
                return edge;
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
    private void checkOnAddEdge(Vertex<T> vertex1, Vertex<T> vertex2) {
        Edge<T> existEdge = this.checkExist(vertex1, vertex2);
        if (existEdge != null) {
            throw new IllegalArgumentException("duplicate edge in this graph");
        }
        if (!vertexs.containsAll(Lists.newArrayList(vertex1, vertex2))) {
            throw new IllegalArgumentException("there is not vertex in vertex aggregate");
        }
    }

    /**
     * 生成一条边
     *
     * @param vertex1 顶点1
     * @param vertex2 顶点2
     * @param weight  权重
     * @return 构建好的边
     */
    private Edge<T> buildEdge(Vertex<T> vertex1, Vertex<T> vertex2, Integer weight) {
        Edge<T> edge = new Edge<>();
        edge.setVertexStart(vertex1);
        edge.setVertexEnd(vertex2);
        edge.setWeight(weight);
        edges.add(edge);
        return edge;
    }

    /**
     * 根据数据获取顶点元素
     *
     * @param data 数据
     * @return 顶点
     */
    public Vertex<T> getVertex(T data) {
        for (Vertex<T> vertex : this.vertexs) {
            if (Objects.equals(vertex.getData(), data)) {
                return vertex;
            }
        }
        return null;
    }


    /**
     * 获取所有的顶点集合
     *
     * @return 所有的顶点
     */
    public List<Vertex<T>> getVertexs() {
        return this.vertexs;
    }

    /**
     * 获取所有的边集合
     *
     * @return 所有边
     */
    public List<Edge<T>> getEdges() {
        return this.edges;
    }


    /**
     * 深度遍历所有逇顶点
     *
     * @param vertex 顶点
     */
    public List<Vertex<T>> dfsErgodic(Vertex<T> vertex) {
        Set<Vertex<T>> visitArray = new HashSet<>();
        List<Vertex<T>> resultList = new ArrayList<>();
        Edge<T> firstEdge = vertex.getFirstEdge();
        resultList.add(vertex);
        visitArray.add(vertex);
        this.dfsSearchVertex(visitArray, vertex, resultList);
        return resultList;
    }

    /**
     * 递归的查找顶点所有的边中没有遍历的点
     *
     * @param visitArray 访问数组，记录访问过的所有顶点
     * @param vertex     顶点
     */
    private void dfsSearchVertex(Set<Vertex<T>> visitArray, Vertex<T> vertex, List<Vertex<T>> resultList) {
        List<Edge<T>> edgesByVertex = vertex.getEdgesByVertex();
        for (Edge<T> edge : edgesByVertex) {
            Vertex<T> edgeNextVertex = edge.getEdgeNextVertex(vertex);
            if (!visitArray.contains(edgeNextVertex)) {
                resultList.add(edgeNextVertex);
                visitArray.add(edgeNextVertex);
                this.dfsSearchVertex(visitArray, edgeNextVertex, resultList);
            }
        }
    }

    /**
     * 广度遍历所有的顶点
     *
     * @param vertex 顶点
     */
    public void bfsErgodic(Vertex<T> vertex) {
        Set<Vertex<T>> visitArray = new HashSet<>();
        Edge<T> firstEdge = vertex.getFirstEdge();
        System.out.println(vertex);
        visitArray.add(vertex);
        this.bfsSearchVertex(visitArray, Lists.newArrayList(vertex));
    }

    /**
     * 递归的查找顶点所有的边中没有遍历的点
     *
     * @param visitArray 访问数组，记录访问过的所有顶点
     * @param allVertex  顶点
     */
    private void bfsSearchVertex(Set<Vertex<T>> visitArray, List<Vertex<T>> allVertex) {
        List<Vertex<T>> needSearchVertexs = new ArrayList<>();
        for (Vertex<T> vertex : allVertex) {
            List<Edge<T>> edgesByVertex = vertex.getEdgesByVertex();
            for (Edge<T> edge : edgesByVertex) {
                Vertex<T> edgeNextVertex = edge.getEdgeNextVertex(vertex);
                if (!visitArray.contains(edgeNextVertex)) {
                    visitArray.add(edgeNextVertex);
                    needSearchVertexs.add(edgeNextVertex);
                    System.out.println(edgeNextVertex);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(needSearchVertexs)) {
            this.bfsSearchVertex(visitArray, needSearchVertexs);
        }
    }

    /**
     * 使用队列实现所有节点的遍历---广度遍历
     *
     * @param vertex 顶点
     */
    public void bfsUseQueue(Vertex<T> vertex) {
        //需要依次遍历的节点集合，当发现没有遍历过的节点，将其存入依次入队列
        Queue<Vertex<T>> queue = new ArrayDeque<>();
        //访问集合
        Set<Vertex<T>> visitSet = new HashSet<>();
        queue.add(vertex);
        visitSet.add(vertex);
        System.out.println(vertex);
        while (!queue.isEmpty()) {
            List<Edge<T>> edgesByVertex = queue.poll().getEdgesByVertex();
            for (Edge<T> edge : edgesByVertex) {
                Vertex<T> edgeNextVertex = edge.getEdgeNextVertex(vertex);
                if (!visitSet.contains(edgeNextVertex)) {
                    visitSet.add(edgeNextVertex);
                    queue.add(edgeNextVertex);
                    System.out.println(edgeNextVertex);
                }
            }
        }
    }

    /**
     * 普利姆算法求最小生成树的边集
     * <p>
     * 方法:从一个点开始 求与之相连的最小边,然后扩大点集,求点集里相连的最小边,且边的两点不能同时存在点集
     *
     * @param vertex 从该点开始生成
     * @return 最小边集合
     */
    public List<Edge<T>> getPrimEdges(Vertex<T> vertex) {
        Set<Vertex<T>> useVertex = new HashSet<>();
        List<Edge<T>> minEdges = new ArrayList<>();
        Set<Edge<T>> canUseEdges = new HashSet<>(vertex.getEdgesByVertex());
        useVertex.add(vertex);
        this.getMinEdgeWeight(canUseEdges, minEdges, useVertex);
        return minEdges;
    }

    /**
     * 普利姆算法求最小生成树的边集 默认使用第一个顶点
     *
     * @return 最小边集合
     */
    public List<Edge<T>> getPrimEdges() {
        return this.getPrimEdges(this.vertexs.get(0));
    }

    /**
     * 递归的扩大普利姆算法的搜索范围
     *
     * @param canUseEdges 可以使用进行比较的边,即与点集相邻的边
     * @param minEdges    最小边集合
     * @param useVertex   使用过的点集
     */
    private void getMinEdgeWeight(Set<Edge<T>> canUseEdges, List<Edge<T>> minEdges, Set<Vertex<T>> useVertex) {
        Edge<T> minWeightEdge = new Edge<>();
        canUseEdges.removeAll(minEdges);
        //此处先让最小边取一个最大值方便后面比较统一处理
        minWeightEdge.setWeight(Integer.MAX_VALUE);
        for (Edge<T> edge : canUseEdges) {
            //边的两点不能同时存在点集,否则会形成闭环
            if (edge.getWeight() < minWeightEdge.getWeight() && !useVertex.containsAll(Sets.newHashSet(edge.getVertexStart(), edge.getVertexEnd()))) {
                minWeightEdge = edge;
            }
        }
        minEdges.add(minWeightEdge);
        this.addCanUseEdges(minWeightEdge, useVertex, canUseEdges);
        if (useVertex.size() == this.getVertexs().size()) {
            return;
        }
        this.getMinEdgeWeight(canUseEdges, minEdges, useVertex);
    }

    /**
     * 构建最小生成树 为可用筛选的边集添加边
     *
     * @param minWeightEdge 上一次比较中查找到的最小边
     * @param useVertex     使用过的点集
     * @param canUseEdges   可以使用进行比较的边,即与点集相邻的边
     */
    private void addCanUseEdges(Edge<T> minWeightEdge, Set<Vertex<T>> useVertex, Set<Edge<T>> canUseEdges) {
        if (!useVertex.contains(minWeightEdge.getVertexStart())) {
            useVertex.add(minWeightEdge.getVertexStart());
            canUseEdges.addAll(new HashSet<>(minWeightEdge.getVertexStart().getEdgesByVertex()));
        } else {
            useVertex.add(minWeightEdge.getVertexEnd());
            canUseEdges.addAll(new HashSet<>(minWeightEdge.getVertexEnd().getEdgesByVertex()));
        }
    }

    /**
     * 克鲁斯卡尔算法求最小生成树
     *
     * @return 最小生成树的边集
     */
    public List<Edge<T>> getKruskalEdges() {
        List<Edge<T>> sortEdges = new ArrayList<>(this.edges);
        sortEdges.sort(new EdgeComparator<>());
        List<Edge<T>> minEdges = new ArrayList<>();
        for (Edge<T> edge : sortEdges) {
            minEdges.add(edge);
            if (this.findIsRing(minEdges, edge)) {
                minEdges.remove(minEdges.size() - 1);
            }
            //最小生成树的边数等于点数-1
            if (minEdges.size() == this.vertexs.size() - 1) {
                break;
            }
        }
        return minEdges;
    }

    /**
     * 查看当前的最小边集合是否已经构成了回路
     *
     * @param minEdges 最小边集
     * @param edge     新增加的边
     * @return 是否构成环
     */
    private boolean findIsRing(List<Edge<T>> minEdges, Edge<T> edge) {
        List<Edge<T>> tempEdges = new ArrayList<>(minEdges);
        tempEdges.remove(edge);
        if (this.hasCallBack(tempEdges, edge.getVertexEnd(), edge.getVertexStart(), edge.getVertexStart())) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否形成了环路
     *
     * @param minEdges     最小的边集合,除去刚新增入的边
     * @param tailVertex   尾节点 新增边的出发节点的另一端
     * @param targetVertex 目标点 即是出发节点
     * @param preVertex    上一次边的另一端 主要用排除已经使用的边
     * @return 是否形成环路
     */
    private boolean hasCallBack(List<Edge<T>> minEdges, Vertex<T> tailVertex, Vertex<T> targetVertex, Vertex<T> preVertex) {
        List<Edge<T>> useEdges = new ArrayList<>();
        //查找最小边集中，tail节点相连的边
        for (Edge<T> minEdge : minEdges) {
            if (Objects.equals(minEdge.getVertexStart(), tailVertex) || Objects.equals(minEdge.getVertexEnd(), tailVertex)) {
                useEdges.add(minEdge);
            }
        }
        for (Edge<T> useEdge : useEdges) {
            //排除掉上一条边, 例如上一条边路径为V3-V4 此时useEdage V4-V3 将这种边排除掉
            if ((Objects.equals(useEdge.getVertexStart(), tailVertex) && Objects.equals(useEdge.getVertexEnd(), preVertex)) ||
                    (Objects.equals(useEdge.getVertexStart(), preVertex) && Objects.equals(useEdge.getVertexEnd(), tailVertex))) {
                continue;
            }
            //相等事表名从开始节点出发可以回到开始节点,即形成了环路
            if (Objects.equals(useEdge.getVertexEnd(), targetVertex) || Objects.equals(useEdge.getVertexStart(), targetVertex)) {
                return true;
            }
            //此处只有true是才返回,为fals时可能顶点还有其他边没有比较故不可返回false 在比较到最小生成树叶子节点时会返回false
            if (this.hasCallBack(minEdges, Objects.equals(useEdge.getVertexStart(), tailVertex) ? useEdge.getVertexEnd() : useEdge.getVertexStart(), targetVertex, Objects.equals(useEdge.getVertexStart(), tailVertex) ? useEdge.getVertexStart() : useEdge.getVertexEnd())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 迪杰斯特拉算法求最短路径 逐渐扩大搜索范围并求出发点到各点距离将所有的点逐渐加入已计算的集合
     * 例如从A出发 if ( 'D 到 B,C,E 的距离' + 'AD 距离' < 'A 到 B,C,E 的距离' ) 此时更新 minRouteMap
     * 用整体代换的思想,先将一个点看作一个整体,然后扩大点集合将点集看做整体与新的点去求最短距离(一定要从最小边开始计算，getMinForCompareMap)
     *
     * @param start 起点
     * @return 起点到各个点的最短路径
     */
    public DijkstraMinRouteResp<T> dijkstraMinRoute(Vertex<T> start) {
        DijkstraMinRouteResp<T> dijkstraMinRouteResp = new DijkstraMinRouteResp<>();
        //用于存储到各个点经过的边路径
        Map<Vertex<T>, List<Edge<T>>> minEdgesMap = new HashMap<>(16);
        //用于保存订单到各个顶点的最短路径
        Map<Vertex<T>, Integer> minRouteMap = this.initMinMap(start, minEdgesMap);
        Set<Vertex<T>> usedVertex = new HashSet<>();
        //遍历所有的顶点 每次取minRouteMap中最小的一个点,且已经计算过的点不参与取最小的比较
        for (int index = 0; index < minRouteMap.size(); index++) {
            Vertex<T> minVertex = this.getMinForCompareMap(minRouteMap, usedVertex);
            usedVertex.add(minVertex);
            //取出与当前点相关的所有边,计算minRouteMap中的值与当前边之和是否小于 minRouteMap中出发点到该点的距离，是则更新minRouteMap
            List<Edge<T>> edgesByVertex = minVertex.getEdgesByVertex();
            for (Edge<T> edge : edgesByVertex) {
                Integer minRoute = minRouteMap.get(minVertex);
                Vertex<T> minVertexNext = edge.getEdgeNextVertex(minVertex);
                if (minRoute + edge.getWeight() < minRouteMap.get(minVertexNext)) {
                    minRouteMap.put(minVertexNext, minRoute + edge.getWeight());
                    List<Edge<T>> min = new ArrayList<>(minEdgesMap.get(minVertex));
                    min.add(edge);
                    minEdgesMap.put(minVertexNext, min);
                }
            }
        }
        dijkstraMinRouteResp.setMinRouteMap(minRouteMap);
        dijkstraMinRouteResp.setMinEdges(minEdgesMap);
        return dijkstraMinRouteResp;
    }


    /**
     * 初始化最短路径集合
     *
     * @param start       起点
     * @param minEdgesMap 用于初始化各个顶点最短路径走过的边集合
     * @return 最短路径集合
     */
    private Map<Vertex<T>, Integer> initMinMap(Vertex<T> start, Map<Vertex<T>, List<Edge<T>>> minEdgesMap) {
        Map<Vertex<T>, Integer> minRouteMap = new HashMap<>(16);
        for (Vertex<T> vertex : this.vertexs) {
            minRouteMap.put(vertex, Integer.MAX_VALUE);
            List<Edge<T>> minEdges = new ArrayList<>();
            minEdgesMap.put(vertex, minEdges);
        }
        minRouteMap.put(start, 0);
        return minRouteMap;
    }

    /**
     * 查找比较集合中权值最小的一个顶点，用于计算当前的最短路径
     *
     * @param compareMap 比较集合
     * @param usedVertex 已经使用过的点不参与下一次比较
     * @return 比较集合中权值最小的顶点
     */
    private Vertex<T> getMinForCompareMap(Map<Vertex<T>, Integer> compareMap, Set<Vertex<T>> usedVertex) {
        Vertex<T> minVertex = new Vertex<>();
        Integer minValue = Integer.MAX_VALUE;
        for (Map.Entry<Vertex<T>, Integer> entry : compareMap.entrySet()) {
            if (usedVertex.contains(entry.getKey())) {
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
     * 弗洛伊德算法求最短路径 返回所有的任意两个点的距离
     * 首先初始化所有点到各个点的距离，无法直接到达的则为最大值
     * 然后遍历所有的顶点,每次加入一个新的顶点，允许该点作为中转点,然后判断经过中转点到达目标点和直接到达目标点的大小,小于则更新最短路径集合
     * 本质上与迪杰斯特拉算法相似,均为从一个点慢慢扩散找相邻的点之间的最短路径
     */
    public FloydMinRouteResp<T> floydMinRoute() {
        FloydMinRouteResp<T> floydMinRouteResp = new FloydMinRouteResp<>();
        Map<Vertex<T>, Map<Vertex<T>, Integer>> floydMinMap = new HashMap<>(16);
        Map<Vertex<T>, Map<Vertex<T>, List<Edge<T>>>> floydMinEdgesMap = new HashMap<>(16);
        this.initfloydMap(floydMinMap, floydMinEdgesMap);
        //joinVertex当前加入的允许中转的点
        for (Vertex<T> joinVertex : vertexs) {
            for (Map.Entry<Vertex<T>, Map<Vertex<T>, Integer>> entry : floydMinMap.entrySet()) {
                //currentVertex当前正在遍历的点
                Vertex<T> currentVertex = entry.getKey();
                Map<Vertex<T>, Integer> vertexMap = entry.getValue();
                if (Objects.equals(currentVertex, joinVertex)) {
                    continue;
                }
                for (Map.Entry<Vertex<T>, Integer> target : vertexMap.entrySet()) {
                    //targetVertex 目标点 由当前点到目标点 求之间距离 算法为当前点->中转点距离+中转点->目标点距离 < 当前点->目标点距离
                    Vertex<T> targetVertex = target.getKey();
                    Integer weight = target.getValue();
                    //由于Inter.MAX_VALUE 想加之后会成为负数,所以当有最大值时无须比较，原有的值一定小于想加后的值
                    if (Objects.equals(vertexMap.get(joinVertex), Integer.MAX_VALUE) || Objects.equals(floydMinMap.get(joinVertex).get(targetVertex), Integer.MAX_VALUE)) {
                        continue;
                    }
                    if (vertexMap.get(joinVertex) + floydMinMap.get(joinVertex).get(targetVertex) < vertexMap.get(targetVertex)) {
                        vertexMap.put(targetVertex, vertexMap.get(joinVertex) + floydMinMap.get(joinVertex).get(targetVertex));
                        List<Edge<T>> tempList = new ArrayList<>(floydMinEdgesMap.get(joinVertex).get(targetVertex));
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
    private void initfloydMap(Map<Vertex<T>, Map<Vertex<T>, Integer>> floydMinMap, Map<Vertex<T>, Map<Vertex<T>, List<Edge<T>>>> floydMinEdgesMap) {
        for (Vertex<T> vertex : this.vertexs) {
            Map<Vertex<T>, Integer> routeMap = new HashMap<>(16);
            Map<Vertex<T>, List<Edge<T>>> minEdgeMap = new HashMap<>(16);
            for (Vertex<T> targetVertex : this.vertexs) {
                routeMap.put(targetVertex, Integer.MAX_VALUE);
                minEdgeMap.put(targetVertex, new ArrayList<>());
            }
            routeMap.put(vertex, 0);
            List<Edge<T>> edgesByVertex = vertex.getEdgesByVertex();
            for (Edge<T> edgeByVertex : edgesByVertex) {
                Vertex<T> edgeNextVertex = edgeByVertex.getEdgeNextVertex(vertex);
                routeMap.put(edgeNextVertex, edgeByVertex.getWeight());
                minEdgeMap.get(edgeNextVertex).add(edgeByVertex);
            }
            floydMinMap.put(vertex, routeMap);
            floydMinEdgesMap.put(vertex, minEdgeMap);
        }
    }
}
