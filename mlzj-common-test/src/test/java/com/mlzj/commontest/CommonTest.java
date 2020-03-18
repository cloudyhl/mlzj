package com.mlzj.commontest;

import com.alibaba.fastjson.JSONObject;
import com.mlzj.commontest.demo.ArrayAggregate;
import com.mlzj.commontest.demo.Iteration;
import com.mlzj.commontest.demo.ListAggregate;
import com.mlzj.commontest.demo.datastruct.MLzjLinkList;
import com.mlzj.commontest.demo.datastruct.MLzjTree;
import com.mlzj.commontest.demo.datastruct.MlzjArrayList;
import com.mlzj.commontest.demo.datastruct.MlzjStack;
import com.mlzj.commontest.demo.datastruct.domain.FloydMinRouteResp;
import com.mlzj.commontest.demo.datastruct.graph.DirectedGraph;
import com.mlzj.commontest.demo.datastruct.graph.UndirectedGraph;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.ArcVertex;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Edge;
import com.mlzj.commontest.demo.datastruct.graph.auxiliary.Vertex;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjList;
import com.mlzj.commontest.model.*;
import com.mlzj.commontest.observe.*;
import com.mlzj.commontest.utils.ClassTools;
import com.mlzj.common.utils.XmlJsonUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;

public class CommonTest {


    @Test
    public void listToSet() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "张三", 20, "四川"));
        users.add(new User(1, "王五", 21, "四川"));
        users.add(new User(1, "赵六", 22, "四川"));
        users.add(new User(1, "钱七", 23, "四川"));
        users.add(new User(1, "哈哈", 24, "四川"));
        users.add(new User(1, "哈哈", 24, "四川"));
        Set<String> collect = users.stream().map(user -> {
            String s = user.getUserName() + user.getAge();
            return s + "!";
        }).collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void classTest() {

    }

    @Test
    public void testValidate() {
        Mobile mobile = new Mobile();
        mobile.setLongSize(-1);
        System.out.println(mobile);
    }


    @Test
    public void testMap() {
        List<String> stringList = new ArrayList<>(300000);
        HashMap map = new HashMap();
        for (int i = 0; i < 1000000; i++) {
            stringList.add("xxxx" + i);
        }
        long l = System.currentTimeMillis();
        stringList.parallelStream().forEach(str -> System.out.println(str));
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void pattern() {
        String property = System.getProperty("file.encoding");
        System.out.println(property);
    }

    @Test
    public void threadTaskPool() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask forkJoinTask = new ForkJoinTask() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        };
    }

    @Test
    public void timeTest() {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.plusDays(-3);
        System.out.println(localDate1);
        System.out.println(localDate + " 08:00:00");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = dateTimeFormatter.format(localDate);
        System.out.println(format);

    }

    @Test
    public void localDateTime() {
        LocalDate localDate = LocalDate.of(2019, 12, 11);
        String localDate1 = LocalDate.now().getYear() + "" + LocalDate.now().getMonth();
        System.out.println(localDate.minusYears(1));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(LocalDateTime.now().format(dateTimeFormatter));

    }



    @Test
    public void getNum() {
        int i = 10;
        int factorial = getFactorial(i);
        System.out.println(factorial);
    }

    private int getFactorial(int num) {

        if (num == 1) {
            return num;
        } else {
            return num * getFactorial(num - 1);
        }
    }

    @Test
    public void studnetTeacher() throws IllegalAccessException, InstantiationException {
        Teacher teacher = new Teacher();
        StudentA studentA = new StudentA();
        StudentB studentB = new StudentB();
        studentA.setTeacher(teacher);
        studentB.setTeacher(teacher);
        teacher.addStudent(studentA).addStudent(studentB);
        teacher.changeStatus("up");
        teacher.changeStatus("down");
        Set<Class<?>> classes = ClassTools.getClasses("com.mlzj.commontest");
        for (Class clazz : classes) {
            if (MlzjEventListener.class.isAssignableFrom(clazz) && clazz != MlzjEventListener.class) {
                System.out.println(clazz.getSimpleName());
                Type[] genericInterfaces = clazz.getGenericInterfaces();
                for (Type type : genericInterfaces) {
                    ParameterizedType type1 = (ParameterizedType) type;

                }
            }

        }
    }

    @Test
    public void testPublisher() {
        long l = System.currentTimeMillis();
        MlzjEventPublish mlzjEventPublish = new MlzjEventPublish("com.mlzj.commontest.observe");
        EventA eventA = new EventA(mlzjEventPublish, "zhangsan", "2019");
        EventB eventB = new EventB(mlzjEventPublish, 12, 20);
        mlzjEventPublish.publishEvent(eventA);
        mlzjEventPublish.publishEvent(eventB);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void testClassLoader() throws IOException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String packageName = "com.mlzj.commontest";
        Set<Class> classes = new HashSet<>();
        Enumeration<URL> resources = contextClassLoader.getResources(packageName.replace(".", "/"));
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if (url.getProtocol().equals("file")) {
                findClassByPackageName(packageName, URLDecoder.decode(url.getPath(), "UTF-8"), classes);
            }
        }
        System.out.println(classes);
    }

    private void findClassByPackageName(String packageName, String classPathName, Set<Class> classes) {
        File dir = new File(classPathName);
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(dirFind -> dirFind.isDirectory() || dirFind.getName().endsWith(".class"));
        for (File file : dirfiles) {
            System.out.println(file.getAbsoluteFile());
            if (file.isDirectory()) {
                findClassByPackageName(packageName.concat(".").concat(file.getName()), file.getAbsolutePath(), classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className);
                    classes.add(aClass);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }


    }

    @Test
    public void optional() {
        List<User> list = new ArrayList<>();
        User userBean = new User();
        userBean.setAddress("浙江温州");
        userBean.setAge(10);
        userBean.setUserName("王五");
        list.add(userBean);
        Optional<List<User>> optionalUsers = Optional.ofNullable(list);
        optionalUsers.ifPresent(users -> users.forEach(user -> System.out.println(user)));
        Optional<Integer> integer = optionalUsers.map(users -> users.get(0).getAge());

    }

    @Test
    public void functionT() {
        CommonTest commonTest = new CommonTest();
        User user = new User();
        user.setAge(1);
        int x = 3;
        int y = 2 + user.getAge();
        Optional<MlzjResultBean> mlzjResultBean = Optional.of(commonTest).map(CommonTest::getParam).map(commonTest::paramTest);
    }

    @Test
    public void iteration() {
        ListAggregate<String> listAggregate = new ListAggregate<>();
        listAggregate.addItem("1").addItem("2").addItem("3").addItem("4");
        Iteration<String> iterator = listAggregate.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        String[] str = new String[5];
        ArrayAggregate<String> arrayAggregate = new ArrayAggregate<>(str);
        arrayAggregate.addItem("1").addItem("2").addItem("3").addItem("4").addItem("5").addItem("6");
        Iteration<String> iterator1 = arrayAggregate.createIterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
    }

    public MlzjParameterBean getParam() {
        int x = 10;
        int y = 20;
        User user = new User();
        user.setAge(10);
        user.setUserName("张三");
        return new MlzjParameterBean().addParameters(x, y, user);
    }

    public MlzjResultBean paramTest(MlzjParameterBean mlzjParameterBean) {
        Integer x = (Integer) mlzjParameterBean.getParam(0);
        Integer y = (Integer) mlzjParameterBean.getParam(1);
        User user = (User) mlzjParameterBean.getParam(2);
        System.out.println(x);
        System.out.println(y);
        System.out.println(user);
        user.setAge(50);
        user.setAddress("长安区");
        return MlzjResultBean.getInstance().addResult(x + y).addResult(user);
    }

    @Test
    public void stringConcatNull() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.getHour());
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:00:00"));
        String str = "str";
        String s = null;
        System.out.println(str + s);
    }

    @Test
    public void sum() {
        int sum = 0;
        long s1 = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            sum = sum + i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - s1);
        long s2 = System.currentTimeMillis();
        System.out.println((1 + 100) * 100 / 2);
        System.out.println(System.currentTimeMillis() - s2);
    }

    @Test
    public void listForEach() {
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);

    }

    @Test
    public void mlzjArrayListTest() {
        MlzjList<String> mlzjList = new MlzjArrayList<>();
        mlzjList.add("1").add("2").add("3").add("4").add("5").add("6").add("7");
        mlzjList.set(2, "2");
        mlzjList.remove("2");

        System.out.println(mlzjList);
        System.out.println(mlzjList.size());
        System.out.println(mlzjList.get(3));
        new LinkedList<>().iterator();
        MlzjList<Integer> mlzjLinkList = new MLzjLinkList<>();
        mlzjLinkList.add(1).add(2).add(3);
        mlzjLinkList.set(1, 20);
        mlzjLinkList.remove(3);
        System.out.println(mlzjLinkList);
    }

    @Test
    public void testMlzjStack() {
        MlzjStack<String> mlzjStack = new MlzjStack<>();
        mlzjStack.push("1").push("2").push("3").push("4").push("5");
        System.out.println(mlzjStack.peek());
        System.out.println(mlzjStack.pop());
        System.out.println(mlzjStack.pop());
        System.out.println(mlzjStack.pop());
        System.out.println(mlzjStack.pop());
        System.out.println(mlzjStack.pop());
    }

    @Test
    public void nullpoint() {
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            arr.add(i);
        }
        long l = System.currentTimeMillis();
        arr.forEach(i -> {
            int x = i / 2;
        });
        System.out.println(System.currentTimeMillis() - l);
        long l1 = System.currentTimeMillis();
        arr.forEach(i -> {
            int x = i >> 1;
        });
        System.out.println(System.currentTimeMillis() - l1);
    }

    @Test
    public void testHashCode() {
        User user = new User();
        user.setAge("12".hashCode());
        user.setAddress("ac");
        System.out.println(user.hashCode());
        int h;
        System.out.println(user.hashCode() ^ user.hashCode() >>> 16);
        System.out.println(~1);

    }

    @Test
    public void testMlzjTree() {
        MLzjTree<String> mLzjTree = new MLzjTree<>();
        Integer code4 = mLzjTree.add("4");
        Integer code2 = mLzjTree.add("2");
        Integer code3 = mLzjTree.add("3");
        Integer code1 = mLzjTree.add("1");
        Integer code6 = mLzjTree.add("6");
        Integer code5 = mLzjTree.add("5");
        Integer code7 = mLzjTree.add("7");
        mLzjTree.removeByCode(code2);
        System.out.println(mLzjTree.getByCode(2));
        System.out.println(mLzjTree.getByCode(code6));
        System.out.println(mLzjTree);
    }

    @Test
    public void removeList() {
        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("4");
        lists.add("2");
        lists.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("4");
        list2.add("5");
        list2.add("6");
        list2.add("7");
        System.out.println(lists.removeAll(list2));
    }

    @Test
    public void testFormayMd() {
        Date date = new Date();
        String mMdd = DateFormatUtils.format(date, "MMdd");
        System.out.println(mMdd);
    }

    @Test
    public void listCopy() {
        List<String> list1 = Lists.newArrayList("1", "2");
        List<String> list2 = new ArrayList<>(list1);
        String s = list1.get(1);
        list1.remove(1);
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(s);
    }

    @Test
    public void encode() throws UnsupportedEncodingException {
        String gbk = URLEncoder.encode("验证码：6666，打死都不要告诉别人哦！", "GBK");
        System.out.println(gbk);
    }

    @Test
    public void undirectedGraph() {
        List<Vertex<String>> vertices = new ArrayList<>();
        UndirectedGraph<String> undirectedGraph = new UndirectedGraph<>();
        vertices.add(undirectedGraph.addVertex("v0"));
        vertices.add(undirectedGraph.addVertex("v1"));
        vertices.add(undirectedGraph.addVertex("v2"));
        vertices.add(undirectedGraph.addVertex("v3"));
        undirectedGraph.addEdge(vertices.get(0), vertices.get(1), 10);
        undirectedGraph.addEdge(vertices.get(0), vertices.get(2), 20);
        undirectedGraph.addEdge(vertices.get(2), vertices.get(3), 14);
        undirectedGraph.addEdge(vertices.get(0), vertices.get(3), 21);
        undirectedGraph.addEdge(vertices.get(2), vertices.get(1), 55);

        undirectedGraph.drawGraph();

        UndirectedGraph<String> undirectedGraph2 = new UndirectedGraph<>();
        List<Vertex<String>> vertices2 = new ArrayList<>();
        vertices2.add(undirectedGraph2.addVertex("v0"));
        vertices2.add(undirectedGraph2.addVertex("v1"));
        vertices2.add(undirectedGraph2.addVertex("v2"));
        vertices2.add(undirectedGraph2.addVertex("v3"));
        undirectedGraph2.addEdgeBuildGraph(vertices2.get(0), vertices2.get(1), null);
        undirectedGraph2.addEdgeBuildGraph(vertices2.get(0), vertices2.get(2), null);
        undirectedGraph2.addEdgeBuildGraph(vertices2.get(2), vertices2.get(3), null);
        undirectedGraph2.addEdgeBuildGraph(vertices2.get(0), vertices2.get(3), null);
        undirectedGraph2.addEdgeBuildGraph(vertices2.get(2), vertices2.get(1), null);
        //undirectedGraph2.delVertex(vertices2.get(2));
        undirectedGraph2.dfsErgodic(vertices2.get(0));
        undirectedGraph.bfsErgodic(vertices2.get(0));
        List<Edge<String>> v0 = undirectedGraph.getVertex("v0").getEdgesByVertex();
        Integer v01 = undirectedGraph.getVertex("v0").getFirstEdge().getWeight();
        v0.remove(undirectedGraph.getVertex("v0").getFirstEdge());
        for (Edge<String> edge : v0) {
            if (edge.getEdgeNextVertex(undirectedGraph.getVertex("v0")).equals(undirectedGraph.getVertex("v3"))) {
                System.out.println(v01 + edge.getWeight());
            }
        }
    }

    @Test
    public void searchForGraph() {
        UndirectedGraph<String> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex("0");
        undirectedGraph.addVertex("1");
        undirectedGraph.addVertex("2");
        undirectedGraph.addVertex("3");
        undirectedGraph.addVertex("4");
        undirectedGraph.addVertex("5");
        undirectedGraph.addVertex("6");
        List<Vertex<String>> vertexs = undirectedGraph.getVertexs();
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(1));
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(2));
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(5));
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(6));
        undirectedGraph.addEdge(vertexs.get(5), vertexs.get(3));
        undirectedGraph.addEdge(vertexs.get(5), vertexs.get(4));
        undirectedGraph.addEdge(vertexs.get(3), vertexs.get(4));
        undirectedGraph.addEdge(vertexs.get(4), vertexs.get(6));
        undirectedGraph.drawGraph();
        System.out.println("dfs------------begin------------------");
        undirectedGraph.dfsErgodic(vertexs.get(1));
        System.out.println("bfs------------begin------------------");
        undirectedGraph.bfsErgodic(vertexs.get(1));
        System.out.println("bfsUseQueue----------begin------------");
        undirectedGraph.bfsUseQueue(vertexs.get(0));
        System.out.println(vertexs);
        System.out.println(undirectedGraph.getEdges());
    }

    @Test
    public void getMinEdgeWeight() {
        long l = System.currentTimeMillis();
        UndirectedGraph<String> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex("v0");
        undirectedGraph.addVertex("v1");
        undirectedGraph.addVertex("v2");
        undirectedGraph.addVertex("v3");
        undirectedGraph.addVertex("v4");
        undirectedGraph.addVertex("v5");
        undirectedGraph.addVertex("v6");
        undirectedGraph.addVertex("v7");
        undirectedGraph.addVertex("v8");
        List<Vertex<String>> vertexs = undirectedGraph.getVertexs();
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(1), 10);
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(5), 11);
        undirectedGraph.addEdge(vertexs.get(1), vertexs.get(2), 18);
        undirectedGraph.addEdge(vertexs.get(1), vertexs.get(8), 12);
        undirectedGraph.addEdge(vertexs.get(1), vertexs.get(6), 16);
        undirectedGraph.addEdge(vertexs.get(2), vertexs.get(8), 8);
        undirectedGraph.addEdge(vertexs.get(2), vertexs.get(3), 22);
        undirectedGraph.addEdge(vertexs.get(8), vertexs.get(3), 21);
        undirectedGraph.addEdge(vertexs.get(6), vertexs.get(3), 24);
        undirectedGraph.addEdge(vertexs.get(6), vertexs.get(7), 19);
        undirectedGraph.addEdge(vertexs.get(3), vertexs.get(4), 20);
        undirectedGraph.addEdge(vertexs.get(3), vertexs.get(7), 16);
        undirectedGraph.addEdge(vertexs.get(7), vertexs.get(4), 7);
        undirectedGraph.addEdge(vertexs.get(4), vertexs.get(5), 26);
        undirectedGraph.addEdge(vertexs.get(6), vertexs.get(5), 17);
        undirectedGraph.drawGraph();
        List<Edge<String>> primEdges = undirectedGraph.getPrimEdges(vertexs.get(5));
        List<Edge<String>> primEdges1 = undirectedGraph.getPrimEdges();

        List<Edge<String>> edges = undirectedGraph.getEdges();
        List<Edge<String>> kruskalEdges = undirectedGraph.getKruskalEdges();
        long count = primEdges.stream().mapToLong(Edge::getWeight).sum();
        long count1 = primEdges1.stream().mapToLong(Edge::getWeight).sum();
        long count2 = kruskalEdges.stream().mapToLong(Edge::getWeight).sum();
        System.out.println(kruskalEdges);
        System.out.println(primEdges);
        System.out.println(count + " " + count1 + " " + count2);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void queue() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        String peek = queue.poll();
        System.out.println(peek);
        System.out.println(queue);
    }

    @Test
    public void testMinRoute() {
        UndirectedGraph<String> undirectedGraph = new UndirectedGraph<>();
        undirectedGraph.addVertex("v0");
        undirectedGraph.addVertex("v1");
        undirectedGraph.addVertex("v2");
        undirectedGraph.addVertex("v3");
        undirectedGraph.addVertex("v4");
        undirectedGraph.addVertex("v5");
        undirectedGraph.addVertex("v6");
        undirectedGraph.addVertex("v7");
        undirectedGraph.addVertex("v8");
        List<Vertex<String>> vertexs = undirectedGraph.getVertexs();
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(1), 1);
        undirectedGraph.addEdge(vertexs.get(0), vertexs.get(2), 5);
        undirectedGraph.addEdge(vertexs.get(1), vertexs.get(2), 3);
        undirectedGraph.addEdge(vertexs.get(1), vertexs.get(3), 7);
        undirectedGraph.addEdge(vertexs.get(1), vertexs.get(4), 5);
        undirectedGraph.addEdge(vertexs.get(2), vertexs.get(4), 1);
        undirectedGraph.addEdge(vertexs.get(2), vertexs.get(5), 7);
        undirectedGraph.addEdge(vertexs.get(3), vertexs.get(4), 2);
        undirectedGraph.addEdge(vertexs.get(4), vertexs.get(5), 3);
        undirectedGraph.addEdge(vertexs.get(3), vertexs.get(6), 3);
        undirectedGraph.addEdge(vertexs.get(4), vertexs.get(6), 6);
        undirectedGraph.addEdge(vertexs.get(4), vertexs.get(7), 9);
        undirectedGraph.addEdge(vertexs.get(5), vertexs.get(7), 5);
        undirectedGraph.addEdge(vertexs.get(6), vertexs.get(7), 2);
        undirectedGraph.addEdge(vertexs.get(6), vertexs.get(8), 7);
        undirectedGraph.addEdge(vertexs.get(7), vertexs.get(8), 4);
        undirectedGraph.drawGraph();
        undirectedGraph.dijkstraMinRoute(vertexs.get(0));
        FloydMinRouteResp<String> stringFloydMinRouteResp = undirectedGraph.floydMinRoute();
    }

    @Test
    public void testBuildDirectedGraph() {
        DirectedGraph<String> directedGraph = new DirectedGraph<>();
        List<ArcVertex<String>> vertices = new ArrayList<>();
        vertices.add(directedGraph.addVertex("v0"));
        vertices.add(directedGraph.addVertex("v1"));
        vertices.add(directedGraph.addVertex("v2"));
        vertices.add(directedGraph.addVertex("v3"));
        vertices.add(directedGraph.addVertex("v4"));
        vertices.add(directedGraph.addVertex("v5"));
        vertices.add(directedGraph.addVertex("v6"));
        directedGraph.addArcEdge(vertices.get(1), vertices.get(2), 1);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(3), 12);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(3), 9);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(4), 3);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(3), 4);
        directedGraph.addArcEdge(vertices.get(3), vertices.get(5), 5);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(5), 13);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(6), 15);
        directedGraph.addArcEdge(vertices.get(5), vertices.get(6), 4);
        directedGraph.drawGraph();
        //directedGraph.delEdge(vertices.get(1), vertices.get(2));
        //directedGraph.delVertex(vertices.get(0));
        //DirectedDijkstraMinRouteResp<String> minRoute = directedGraph.getDijkstraMinRoute(vertices.get(2));
        directedGraph.floydMinRoute();
    }

    @Test
    public void topologySort() {
        long start = System.currentTimeMillis();
        DirectedGraph<String> directedGraph = new DirectedGraph<>();
        List<ArcVertex<String>> vertices = new ArrayList<>();
        vertices.add(directedGraph.addVertex("v0"));
        vertices.add(directedGraph.addVertex("v1"));
        vertices.add(directedGraph.addVertex("v2"));
        vertices.add(directedGraph.addVertex("v3"));
        vertices.add(directedGraph.addVertex("v4"));
        vertices.add(directedGraph.addVertex("v5"));
        vertices.add(directedGraph.addVertex("v6"));
        vertices.add(directedGraph.addVertex("v7"));
        vertices.add(directedGraph.addVertex("v8"));
        vertices.add(directedGraph.addVertex("v9"));
        vertices.add(directedGraph.addVertex("v10"));
        vertices.add(directedGraph.addVertex("v11"));
        vertices.add(directedGraph.addVertex("v12"));
        vertices.add(directedGraph.addVertex("v13"));
        directedGraph.addArcEdge(vertices.get(0), vertices.get(4));
        directedGraph.addArcEdge(vertices.get(0), vertices.get(5));
        directedGraph.addArcEdge(vertices.get(0), vertices.get(11));
        directedGraph.addArcEdge(vertices.get(1), vertices.get(4));
        directedGraph.addArcEdge(vertices.get(1), vertices.get(8));
        directedGraph.addArcEdge(vertices.get(1), vertices.get(2));
        directedGraph.addArcEdge(vertices.get(2), vertices.get(5));
        directedGraph.addArcEdge(vertices.get(2), vertices.get(6));
        directedGraph.addArcEdge(vertices.get(3), vertices.get(2));
        directedGraph.addArcEdge(vertices.get(3), vertices.get(13));
        directedGraph.addArcEdge(vertices.get(4), vertices.get(7));
        directedGraph.addArcEdge(vertices.get(5), vertices.get(8));
        directedGraph.addArcEdge(vertices.get(5), vertices.get(12));
        directedGraph.addArcEdge(vertices.get(8), vertices.get(7));
        directedGraph.addArcEdge(vertices.get(9), vertices.get(10));
        directedGraph.addArcEdge(vertices.get(9), vertices.get(11));
        directedGraph.addArcEdge(vertices.get(10), vertices.get(13));
        directedGraph.addArcEdge(vertices.get(12), vertices.get(9));
        directedGraph.drawGraph();
        List<ArcVertex<String>> vertices1 = directedGraph.topologySort();
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testCruxRoute(){
        DirectedGraph<String> directedGraph = new DirectedGraph<>();
        List<ArcVertex<String>> vertices = new ArrayList<>();
        vertices.add(directedGraph.addVertex("v0"));
        vertices.add(directedGraph.addVertex("v1"));
        vertices.add(directedGraph.addVertex("v2"));
        vertices.add(directedGraph.addVertex("v3"));
        vertices.add(directedGraph.addVertex("v4"));
        vertices.add(directedGraph.addVertex("v5"));
        vertices.add(directedGraph.addVertex("v6"));
        vertices.add(directedGraph.addVertex("v7"));
        vertices.add(directedGraph.addVertex("v8"));
        vertices.add(directedGraph.addVertex("v9"));
        directedGraph.addArcEdge(vertices.get(0), vertices.get(1),3);
        directedGraph.addArcEdge(vertices.get(0), vertices.get(2),4);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(3),8);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(5),7);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(4),6);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(3),5);
        directedGraph.addArcEdge(vertices.get(3), vertices.get(4),3);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(6),9);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(7),4);
        directedGraph.addArcEdge(vertices.get(5), vertices.get(7),6);
        directedGraph.addArcEdge(vertices.get(6), vertices.get(9),2);
        directedGraph.addArcEdge(vertices.get(7), vertices.get(8),5);
        directedGraph.addArcEdge(vertices.get(8), vertices.get(9),3);
        directedGraph.drawGraph();
        directedGraph.cruxRoute();
    }

    @Test
    public void testDfsDireted(){
        DirectedGraph<String> directedGraph = new DirectedGraph<>();
        List<ArcVertex<String>> vertices = new ArrayList<>();
        vertices.add(directedGraph.addVertex("v0"));
        vertices.add(directedGraph.addVertex("v1"));
        vertices.add(directedGraph.addVertex("v2"));
        vertices.add(directedGraph.addVertex("v3"));
        vertices.add(directedGraph.addVertex("v4"));
        vertices.add(directedGraph.addVertex("v5"));
        vertices.add(directedGraph.addVertex("v6"));
        vertices.add(directedGraph.addVertex("v7"));
        vertices.add(directedGraph.addVertex("v8"));
        vertices.add(directedGraph.addVertex("v9"));
        directedGraph.addArcEdge(vertices.get(0), vertices.get(1),3);
        directedGraph.addArcEdge(vertices.get(0), vertices.get(2),4);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(3),8);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(5),7);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(4),6);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(3),5);
        directedGraph.addArcEdge(vertices.get(3), vertices.get(4),3);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(6),9);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(7),4);
        directedGraph.addArcEdge(vertices.get(5), vertices.get(7),6);
        directedGraph.addArcEdge(vertices.get(6), vertices.get(9),2);
        directedGraph.addArcEdge(vertices.get(7), vertices.get(8),5);
        directedGraph.addArcEdge(vertices.get(8), vertices.get(9),3);
        directedGraph.drawGraph();
        List<ArcVertex<String>> vertices1 = directedGraph.dfsErgodic(vertices.get(0));
        List<ArcVertex<String>> vertices2 = directedGraph.bfsErgodic(vertices.get(0));
        System.out.println(vertices1);
        System.out.println(vertices2);
    }

    @Test
    public void testTreeAdd(){
        MLzjTree<Integer> mLzjTree = new MLzjTree<>();
        mLzjTree.addForAvl(3);
        mLzjTree.addForAvl(2);
        mLzjTree.addForAvl(1);
        mLzjTree.addForAvl(4);
        mLzjTree.addForAvl(5);
        mLzjTree.addForAvl(6);
        mLzjTree.addForAvl(7);
        mLzjTree.addForAvl(10);
        mLzjTree.addForAvl(9);
        mLzjTree.addForAvl(8);
        mLzjTree.removeByDataAvl(4);

        System.out.println(mLzjTree);
        System.out.println(mLzjTree.getByCode(2));
    }

    @Test
    public void testConcurrent(){
        String str = "0010100200000";
        String t = str.replaceAll("0+$", "");

        String scity = "371100";
        String prefix = scity.substring(0,4);
        String target = "3711300";
        System.out.println(!(target.length() - target.replaceAll("0+$", "").length() >1));
    }

    @Test
    public void setSortString(){
        String s1 = "2019";
        String s2 = "2018";
        String s3 = "2017";
        Set<String> set = new TreeSet<>();
        set.add(s3);
        set.add(s2);
        set.add(s1);
        for (String s : set){
            System.out.println(s);
        }
    }

    @Test
    public void weak(){
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.YEAR, -1);
    }

    @Test
    public void jsonToXml() throws JSONException, IOException {
        UserInfo user = new UserInfo();
        Dog dog = new Dog();
        dog.setDogAge(1);
        dog.setDogName("dog");
        user.setDog(dog);
        user.setUserName("123");
        Order order1 = new Order();
        order1.setId(1);
        order1.setComment("order1");
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        Order order2 = new Order();
        order2.setComment("order2");
        order2.setId(2);
        orders.add(order2);
        user.setOrders(orders);
        System.out.println(user);
        String s = XmlJsonUtils.json2xml(JSONObject.toJSONString(user));
        System.out.println(s);
        System.out.println("--------------------------");
        String out = XmlJsonUtils.obj2Xml(user);
        System.out.println(out);

    }

    @Test
    public void testStringContains(){
        String str = "量是,多少,sadsa";
        System.out.println(str.replace(",","%"));
    }

    @Test
    public void localtime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String endTime = formatter.format(LocalDate.now().plusMonths(1)).concat("-").concat("01 00:00:00");
        String startTime = formatter.format(LocalDate.now().plusYears(-1)).concat("-").concat("01 00:00:00");
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void doubleCaculate(){
        System.out.println((46.7-303.02)/303.02*100);
        System.out.println((47.4-557.140000)/557.140000*100);
        System.out.println((3106.3-557.140000)/557.140000*100);
    }


}
