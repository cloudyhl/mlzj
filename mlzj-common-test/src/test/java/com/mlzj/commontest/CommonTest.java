package com.mlzj.commontest;

import com.mlzj.commontest.demo.ArrayAggregate;
import com.mlzj.commontest.demo.Iteration;
import com.mlzj.commontest.demo.ListAggregate;
import com.mlzj.commontest.demo.datastruct.MLzjLinkList;
import com.mlzj.commontest.demo.datastruct.MLzjTree;
import com.mlzj.commontest.demo.datastruct.MlzjArrayList;
import com.mlzj.commontest.demo.datastruct.MlzjStack;
import com.mlzj.commontest.demo.datastruct.interfaces.MlzjList;
import com.mlzj.commontest.model.*;
import com.mlzj.commontest.observe.*;
import com.mlzj.commontest.utils.ClassTools;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLDecoder;
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
    public void cloneTest() throws CloneNotSupportedException {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(10);
        userInfo.setUserName("张三");
        Dog dog = new Dog();
        dog.setDogAge(11);
        dog.setDogName("wangcai");
        userInfo.setDog(dog);
        Object clone = userInfo.clone();
        dog.setDogAge(9);
        userInfo.setAge(9);
        System.out.println(clone);


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
        for (int i = 0; i < 1000000; i++){
            arr.add(i);
        }
        long l = System.currentTimeMillis();
        arr.forEach(i-> {int x = i/2;});
        System.out.println(System.currentTimeMillis() - l);
        long l1 = System.currentTimeMillis();
        arr.forEach(i-> {int x = i>>1;});
        System.out.println(System.currentTimeMillis() - l1);
    }

    @Test
    public void testHashCode(){
        User user = new User();
        user.setAge("12".hashCode());
        user.setAddress("ac");
        System.out.println(user.hashCode());
        int h;
        System.out.println(user.hashCode()^user.hashCode()>>>16);
        System.out.println(~1);

    }

    @Test
    public void testMlzjTree(){
        MLzjTree<String> mLzjTree = new MLzjTree<>();
        Integer code4 = mLzjTree.add("4");
        Integer code2 = mLzjTree.add("2");
        Integer code3 = mLzjTree.add("3");
        Integer code1 = mLzjTree.add("1");
        Integer code6 = mLzjTree.add("6");
        Integer code5 = mLzjTree.add("5");
        Integer code7 = mLzjTree.add("7");
        mLzjTree.removeByCode(code2);
        System.out.println(mLzjTree.getByCode(code6));
        System.out.println(mLzjTree);
    }

    @Test
    public void removeList(){
        List<String> lists = new ArrayList<>();
        lists.add("1");
        lists.add("4");
        lists.add("2");
        lists.add("3");
        lists.remove(2);
        System.out.println(lists);
    }
    @Test
    public void testFormayMd(){
        Date date = new Date();
        String mMdd = DateFormatUtils.format(date, "MMdd");
        System.out.println(mMdd);
    }
}
