package com.mlzj.commontest;

import com.mlzj.commontest.demo.DemoExecute;
import com.mlzj.commontest.event.TestEvent;
import com.mlzj.commontest.model.Order;
import com.mlzj.commontest.model.User;
import com.mlzj.commontest.proxy.handler.CatProxyHandler;
import com.mlzj.commontest.proxy.interfaces.Animal;
import com.mlzj.commontest.proxy.interfaces.Dynamic;
import com.mlzj.commontest.proxy.model.Cat;
import com.mlzj.commontest.service.OrderService;
import com.mlzj.commontest.service.UserService;
import com.mlzj.commontest.service.impl.DemoServiceImpl;
import com.mlzj.commontest.utils.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjCommonTestApplicationTests {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Resource
    private EventPublisher eventPublisher;

    @Resource
    private ResourceLoader resourceLoader;

    @Resource
    private DemoServiceImpl service;

    @Resource
    private Dynamic simpleDynamic;

    @Resource
    private ApplicationContext applicationContext;


    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;
    @Resource
    private DemoExecute demoExecute;

    @Test
    public void contextLoads() throws InterruptedException {
        eventPublisher.publisherEvent(new TestEvent(eventPublisher,"123213","1232131"));
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void testAsyncMethodExecute() throws InterruptedException {
        demoExecute.execute();
        System.out.println("async method is process");
        TimeUnit.SECONDS.sleep(10);
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void usTransactionPush(){
        User user = new User();
        user.setAddress("四川");
        user.setAge(10);
        user.setUserName("wangwu");
        userService.saveUser(user);
        eventPublisher.publisherEvent(new TestEvent(eventPublisher,"transaction","transaction is go"));
        Order order = new Order();
        //int i = 1/0;
        order.setOrderBn("123");
        order.setComment("good");
        orderService.saveOrder(order);
    }

    @Test
    @Transactional
    @Rollback(value = false) //在test类中使用transaction注解会导致无法保存数据库，spring-test默认会回滚
    public void usTransaction() throws InterruptedException {
        this.usTransactionPush();
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void testThreadLocal(){
        threadLocal.set("abc");
        threadLocal.set("12321");
        new Thread(()->{
            threadLocal.set("1");
            getThreadLocal();
        }).start();
        new Thread(()->{
            threadLocal.set("2");
            getThreadLocal();
        }).start();
        System.out.println(threadLocal.get());
    }

    private void getThreadLocal(){
        System.out.println(""+Thread.currentThread().getId()+threadLocal.get());
    }

    @Test
    public void testList(){
        List<User> list = new ArrayList<>();
        list.add(new User(1,"abc",11,"xxa"));
        list.add(new User(2,"abd",11,"xxb"));
        list.add(new User(3,"abe",11,"xxc"));
        list.add(new User(4,"abf",11,"xxd"));
        list.add(new User(5,"abg",11,"xxe"));
        Predicate<User> xxx = (User user) -> user.getAge() > 10 && user.getUserName().equals("xxx");
        //stream中使用过滤器定义
        Predicate<User> abc = (User user) -> user.getUserName().equals("abc") && user.getAge() == 11;
        Map<String, String> collect = list.stream().filter(abc).collect(Collectors.toMap(User::getUserName, User::getAddress));
        List<Integer> collect1 = list.stream().map(User::getAge).distinct().collect(Collectors.toList());
        String collect2 = list.stream().map(User::getAddress).collect(Collectors.joining("------"));
        System.out.println(collect2);
        boolean b = list.stream().allMatch(abc);
        System.out.println(b);
        System.out.println(collect1);
        list.forEach(user -> System.out.println(user));
        System.out.println(collect );
    }
    @Test
    public void testMap(){
        Map<String, String> hashMap = new HashMap<>(16);
        hashMap.put("yhl","123");
        hashMap.put("lx","124");
        hashMap.put("hs","125");
        hashMap.put("yzc","126");
        hashMap.forEach((x,y)->{
            System.out.println(x);
            System.out.println(y);
        });
    }
    @Test
    public void testSet(){
        Set<String> stringSet = new HashSet<>();
        stringSet.add("abc");
        stringSet.add("abd");
        stringSet.add("abe");
        stringSet.add("abf");
        stringSet.add("abg");
        List<String> abc = stringSet.stream().filter(s -> s.equals("abc")).collect(Collectors.toList());
        System.out.println(abc);
    }

    @Test
    public void testDemoProxy(){
        CatProxyHandler catProxyHandler = new CatProxyHandler();
        Animal proxy = (Animal) catProxyHandler.getProxy(new Cat("1","1"));
        proxy.say();
    }

    @Test
    public void testCat(){
        String s = simpleDynamic.findSimpleCat();
        System.out.println(s);
        System.out.println(simpleDynamic.findSimpleCatByName("husen"));
        System.out.println( simpleDynamic.baseSelect());
        //System.out.println(dynamic);
    }

    @Test
    public void classPathLoader() throws IOException {
        String packageName = "com.mlzj.commontest.observe";
        ResourcePatternResolver resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
        org.springframework.core.io.Resource[] resources = resourcePatternResolver.getResources("classpath*:".concat(packageName.replace(".", "/").concat("/**/*.class")));
        for (org.springframework.core.io.Resource resource : resources){
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
            String className = metadataReader.getClassMetadata().getClassName();
            System.out.println(className);
        }
    }
}
