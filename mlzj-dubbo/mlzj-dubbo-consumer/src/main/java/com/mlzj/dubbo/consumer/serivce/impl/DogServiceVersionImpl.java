package com.mlzj.dubbo.consumer.serivce.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mlzj.dubbo.interfaces.service.animal.DogService;
import com.mlzj.dubbo.interfaces.vo.Dog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhl
 * @date 2019/4/22
 */
@Component
@Service(version = "1.1")
public class DogServiceVersionImpl implements DogService {
    private static int count = 0;

    private ArrayList<Dog> dogs = new ArrayList<>();


    @Override
    public List<Dog> selectDogs() throws InterruptedException {
        dogs.add(new Dog("wangcai","zangsan"));
        System.out.println(count++);
        return dogs;
    }


    @Override
    public List<Dog> selectDogCache(String name) {
        System.out.println(count++);
        return dogs;
    }

    @Override
    public List<Dog> selectDogCache(String name, String owner) {
        System.out.println(count++);
        return dogs;
    }
}
