package com.mlzj.commontest;

import com.alibaba.fastjson.JSONObject;
import com.mlzj.common.utils.XmlJsonUtils;
import com.mlzj.commontest.demo.ArrayAggregate;
import com.mlzj.commontest.demo.Iteration;
import com.mlzj.commontest.demo.ListAggregate;
import com.mlzj.commontest.demo.concurrent.test.LockObject;
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
import com.mlzj.commontest.service.impl.UserServiceImpl;
import com.mlzj.commontest.utils.ClassTools;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import io.swagger.models.auth.In;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
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
    public void testCruxRoute() {
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
        directedGraph.addArcEdge(vertices.get(0), vertices.get(1), 3);
        directedGraph.addArcEdge(vertices.get(0), vertices.get(2), 4);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(3), 8);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(5), 7);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(4), 6);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(3), 5);
        directedGraph.addArcEdge(vertices.get(3), vertices.get(4), 3);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(6), 9);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(7), 4);
        directedGraph.addArcEdge(vertices.get(5), vertices.get(7), 6);
        directedGraph.addArcEdge(vertices.get(6), vertices.get(9), 2);
        directedGraph.addArcEdge(vertices.get(7), vertices.get(8), 5);
        directedGraph.addArcEdge(vertices.get(8), vertices.get(9), 3);
        directedGraph.drawGraph();
        directedGraph.cruxRoute();
    }

    @Test
    public void testDfsDireted() {
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
        directedGraph.addArcEdge(vertices.get(0), vertices.get(1), 3);
        directedGraph.addArcEdge(vertices.get(0), vertices.get(2), 4);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(3), 8);
        directedGraph.addArcEdge(vertices.get(2), vertices.get(5), 7);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(4), 6);
        directedGraph.addArcEdge(vertices.get(1), vertices.get(3), 5);
        directedGraph.addArcEdge(vertices.get(3), vertices.get(4), 3);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(6), 9);
        directedGraph.addArcEdge(vertices.get(4), vertices.get(7), 4);
        directedGraph.addArcEdge(vertices.get(5), vertices.get(7), 6);
        directedGraph.addArcEdge(vertices.get(6), vertices.get(9), 2);
        directedGraph.addArcEdge(vertices.get(7), vertices.get(8), 5);
        directedGraph.addArcEdge(vertices.get(8), vertices.get(9), 3);
        directedGraph.drawGraph();
        List<ArcVertex<String>> vertices1 = directedGraph.dfsErgodic(vertices.get(0));
        List<ArcVertex<String>> vertices2 = directedGraph.bfsErgodic(vertices.get(0));
        System.out.println(vertices1);
        System.out.println(vertices2);
    }

    @Test
    public void testTreeAdd() {
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
    public void testReadList() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("1111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //thread.join();
        System.out.println("go");
    }

    @Test
    public void main() throws Exception {
// sleepThread???????
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
// busyThread?????
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
// ??5???sleepThread?busyThread????
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        System.out.println(busyThread.isInterrupted());
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
// ??sleepThread?busyThread????
        TimeUnit.SECONDS.sleep(2);
    }

    class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

    @Test
    public void testHashMapForManyThread() throws InterruptedException {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }

    @Test
    public void concurrentList() throws ParseException {
        String path = "F:\\new";
        File base= new File(path);
        if (base.isDirectory()){
            for (String s : base.list()) {
                System.out.println(s);
            }
        }
        String fileName = "2019-01-13-17-00.jpg";
        System.out.println(fileName.split("\\.")[0]);
        System.out.println(base.exists());

        List<String> betweenDates = getBetweenDates("2019-01-01 08:00:00", "2020-01-01 08:00:00");
        List<String> newDates = new ArrayList<>();
        betweenDates.forEach(item -> {
            newDates.add(item.replace("-", "\\"));
        });
        System.out.println(newDates);

    }

    private List<String> getBetweenDatess(String start, String end) throws ParseException {

        List<String> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(DateUtils.parseDate(start, "yyyy-MM-dd HH:mm:ss"));
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(DateUtils.parseDate(end, "yyyy-MM-dd HH:mm:ss"));
        result.add(start.substring(0,10));
        while (tempStart.before(tempEnd)) {
            result.add(DateFormatUtils.format(tempStart.getTime(),"yyyy-MM-dd"));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    @Test
    public void listStringTest(){
        List<Integer> listString= Lists.newArrayList(1,2,3);
        double sum = listString.stream().mapToDouble(x -> x).sum();
        System.out.println(sum);
        System.out.println(listString);
        final User s = new User();
        listString.forEach(item-> s.setAddress("1"));
        System.out.println(s);
        System.out.println(StringUtils.compare("2020-01-15-00-00", "2020-01-15-00-40"));
    }

    @Test
    public void testConcurrent() {
        String str = "0010100200000";
        String t = str.replaceAll("0+$", "");

        String scity = "371100";
        String prefix = scity.substring(0, 4);
        String target = "3711300";
        System.out.println(!(target.length() - target.replaceAll("0+$", "").length() > 1));
    }

    @Test
    public void setSortString() {
        String s1 = "2019";
        String s2 = "2018";
        String s3 = "2017";
        Set<String> set = new TreeSet<>();
        set.add(s3);
        set.add(s2);
        set.add(s1);
        for (String s : set) {
            System.out.println(s);
        }
    }

    @Test
    public void weak() {
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
    public void testStringContains() {
        String str = "量是,多少,sadsa";
        System.out.println(str.replace(",", "%"));
    }

    @Test
    public void localtime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String endTime = formatter.format(LocalDate.now().plusMonths(1)).concat("-").concat("01 00:00:00");
        String startTime = formatter.format(LocalDate.now().plusYears(-1)).concat("-").concat("01 00:00:00");
        System.out.println(startTime);
        System.out.println(endTime);
    }

    @Test
    public void doubleCaculate() {
        System.out.println((46.7 - 303.02) / 303.02 * 100);
        System.out.println((47.4 - 557.140000) / 557.140000 * 100);
        System.out.println((3106.3 - 557.140000) / 557.140000 * 100);
    }

    @Test
    public void data() {
        String data = "2015/10/1 0:00,0.5;2015/10/1 1:00,0.5;2015/10/1 2:00,0.5;2015/10/1 3:00,0.5;2015/10/1 4:00,0.5;2015/10/1 5:00,0.5;2015/10/1 6:00,0.5;2015/10/1 7:00,0.5;2015/10/1 8:00,0.5;2015/10/1 9:00,0.5;2015/10/1 10:00,0.5;2015/10/1 11:00,0.5;2015/10/1 12:00,0.5;2015/10/1 13:00,0.5;2015/10/1 14:00,0.5;2015/10/1 15:00,0.5;2015/10/1 16:00,0.5;2015/10/1 17:00,0.5;2015/10/1 18:00,0.5;2015/10/1 19:00,0.5;2015/10/1 20:00,0.5;2015/10/1 21:00,0.5;2015/10/1 22:00,0.5;2015/10/1 23:00,0.5;2015/10/2 0:00,0.5;2015/10/2 1:00,0.5;2015/10/2 2:00,0.5;2015/10/2 3:00,0.5;2015/10/2 4:00,0.5;2015/10/2 5:00,1;2015/10/2 6:00,4;2015/10/2 7:00,10;2015/10/2 8:00,15;2015/10/2 9:00,20;2015/10/2 10:00,25;2015/10/2 11:00,32;2015/10/2 12:00,42;2015/10/2 13:00,55;2015/10/2 14:00,81;2015/10/2 15:00,212;2015/10/2 16:00,363;2015/10/2 17:00,330;2015/10/2 18:00,237;2015/10/2 19:00,155;2015/10/2 20:00,100;2015/10/2 21:00,68;2015/10/2 22:00,52;2015/10/2 23:00,44;2015/10/3 0:00,41;2015/10/3 1:00,38;2015/10/3 2:00,36;2015/10/3 3:00,34;2015/10/3 4:00,33;2015/10/3 5:00,32;2015/10/3 6:00,31;2015/10/3 7:00,30;2015/10/3 8:00,29;2015/10/3 9:00,28;2015/10/3 10:00,27;2015/10/3 11:00,26;2015/10/3 12:00,26;2015/10/3 13:00,25;2015/10/3 14:00,24;2015/10/3 15:00,23;2015/10/3 16:00,23;2015/10/3 17:00,22;2015/10/3 18:00,21;2015/10/3 19:00,21;2015/10/3 20:00,20;2015/10/3 21:00,20;2015/10/3 22:00,19;2015/10/3 23:00,18;2015/10/4 0:00,18;2015/10/4 1:00,17;2015/10/4 2:00,17;2015/10/4 3:00,16;2015/10/4 4:00,16;2015/10/4 5:00,15;2015/10/4 6:00,14;2015/10/4 7:00,14;2015/10/4 8:00,14;2015/10/4 9:00,14;2015/10/4 10:00,14;2015/10/4 11:00,14;2015/10/4 12:00,14;2015/10/4 13:00,14;2015/10/4 14:00,14;2015/10/4 15:00,14;2015/10/4 16:00,14;2015/10/4 17:00,14;2015/10/4 18:00,14;2015/10/4 19:00,14;2015/10/4 20:00,14;2015/10/4 21:00,14;2015/10/4 22:00,14;2015/10/4 23:00,14;2015/10/5 0:00,14;2015/10/5 1:00,14;2015/10/5 2:00,14;2015/10/5 3:00,14;2015/10/5 4:00,14;2015/10/5 5:00,14;2015/10/5 6:00,14;2015/10/5 7:00,14;2015/10/5 8:00,14;2015/10/5 9:00,14";
        String[] split = data.split(";");
        for (String s : split) {
            String[] split1 = s.split(",");
            System.out.println("时间:" + split1[0] + "   数据:" + split1[1]);
        }
    }

    @Test
    public void strCompare() {
        String s1 = "238";
        String s2 = "38";
        System.out.println(StringUtils.compare(s1, s2));
    }

    @Test
    public void getBetweenDates() throws ParseException {
        List<String> betweenDates = this.getBetweenDates("2020-03-28 08:00:00", "2020-03-29 01:00:00");
        System.out.println(betweenDates);
    }


    public static List<String> getBetweenDates(String start, String end) throws ParseException {

        end = end.substring(0, 11) + "24:00:00";
        List<String> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(DateUtils.parseDate(start, "yyyy-MM-dd HH:mm:ss"));
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(DateUtils.parseDate(end, "yyyy-MM-dd HH:mm:ss"));
        result.add(start.substring(0, 10));
        while (tempStart.before(tempEnd)) {
            result.add(DateFormatUtils.format(tempStart.getTime(), "yyyy-MM-dd"));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    @Test
    public void testFile() {
        File file = new File("F:\\2020\\01\\13\\808BC076");
        if (file.exists() && file.isDirectory()) {
            for (String fileName : file.list()) {
                String s = "2020-03-30 18:00:00";
                String s1 = s.substring(0, 16).replace(":", "-").replace(" ", "-");
                String s2 = fileName.split("\\.")[0];
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(StringUtils.compare(s2, s1) >= 0);
            }

        }
    }

    @Test
    public void replice() {
        String str = "SELECT a.adnm,b.* FROM (select NoticeId,\n" +
                "        stuff((select ','+adnm from  (SELECT a.*,b.adnm FROM (SELECT a.* FROM sys_notice_sx a, (SELECT '%'+adcd+'%' as adcd FROM Fn_Adcd_Child('500103001000000')) b WHERE a.adcd like b.adcd) a,(SELECT'%' + adcd + '%' as adcd,adnm FROM AD_CD_B) b WHERE a.adcd like b.adcd) A  \n" +
                "            where A.NoticeId=B.NoticeId \n" +
                "            for xml path('')),1,1,'') as adnm   \n" +
                " from (SELECT a.*,b.adnm FROM (SELECT a.* FROM sys_notice_sx a, (SELECT '%'+adcd+'%' as adcd FROM Fn_Adcd_Child('500103001000000')) b WHERE a.adcd like b.adcd) a,(SELECT'%' + adcd + '%' as adcd,adnm FROM AD_CD_B) b WHERE a.adcd like b.adcd) B   \n" +
                " group by B.NoticeId  ) A , (SELECT a.* FROM sys_notice_sx a, (SELECT '%'+adcd+'%' as adcd FROM Fn_Adcd_Child('500103001000000')) b WHERE a.adcd like b.adcd AND ReleaseDate <= ? AND ReleaseDate > ?) B WHERE A.NoticeId = b.NoticeId ORDER BY B.ReleaseDate DESC";
        System.out.println(str.replace("AND ReleaseDate <= ?", ""));
    }

    @Test
    public void testLock() throws InterruptedException {
        LockObject lockObject = new LockObject();
        Thread thread = new Thread(() -> {
            try {
                lockObject.go();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        lockObject.get();
    }

    @Test
    public void testObjSize() {
        Object[] objects = this.objSize(1, 2, 3, 4, 5);
        for (Object o : objects) {
            System.out.println(o);
        }
    }

    public Object[] objSize(Object... obj) {
        return obj;
    }


    @Test
    public void testRemoveOnForEach() {

    }

    public static void main(String[] args) {
    }


}
