package com.mlzj.commontest;

import com.google.common.collect.Lists;
import com.mlzj.commontest.model.Mobile;
import com.mlzj.commontest.model.User;
import com.mlzj.commontest.proxy.model.Cat;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CommonTest {


    @Test
    public void listToSet(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"张三",20,"四川"));
        users.add(new User(1,"王五",21,"四川"));
        users.add(new User(1,"赵六",22,"四川"));
        users.add(new User(1,"钱七",23,"四川"));
        users.add(new User(1,"哈哈",24,"四川"));
        users.add(new User(1,"哈哈",24,"四川"));
        Set<String> collect = users.stream().map(user -> {
            String s = user.getUserName() + user.getAge();
            return s+"!";
        }).collect(Collectors.toSet());
        System.out.println(collect);
    }

    @Test
    public void classTest(){

    }

    @Test
    public void testValidate(){
        Mobile mobile = new Mobile();
        mobile.setLongSize(-1);
        System.out.println(mobile);
    }


    @Test
    public void testMap(){
        List<String> stringList = new ArrayList<>(300000);
        HashMap map = new HashMap();
        for (int i = 0;i<1000000;i++){
            stringList.add("xxxx"+i);
        }
        long l = System.currentTimeMillis();
        stringList.parallelStream().forEach(str-> System.out.println(str));
        System.out.println(System.currentTimeMillis()-l);
    }

    @Test
    public void pattern(){
        String property = System.getProperty("file.encoding");
        System.out.println(property);
    }
}
