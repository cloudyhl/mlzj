package com.mlzj.commontest;

import com.mlzj.commontest.model.User;
import com.mlzj.commontest.proxy.model.Cat;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
}
