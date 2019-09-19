package com.mlzj.commontest;

import com.mlzj.commontest.model.Dog;
import com.mlzj.commontest.model.Mobile;
import com.mlzj.commontest.model.User;
import com.mlzj.commontest.model.UserInfo;
import com.mlzj.commontest.observe.*;
import com.mlzj.commontest.utils.ClassTools;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
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
                    Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+className);
                    classes.add(aClass);
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }


            }
        }


    }

    @Test
    public void optional(){
        List<User> list = new ArrayList<>();
        User userBean = new User();
        userBean.setAddress("浙江温州");
        userBean.setAge(10);
        userBean.setUserName("王五");
        list.add(userBean);
        Optional<List<User>> optionalUsers = Optional.ofNullable(list);
        optionalUsers.ifPresent(users-> users.forEach(user-> System.out.println(user)));
        Optional<Integer> integer = optionalUsers.map(users -> users.get(0).getAge());

    }


}
