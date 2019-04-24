package com.mlzj.dubbo.consumer.serivce.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mlzj.dubbo.interfaces.service.animal.DogService;
import com.mlzj.dubbo.interfaces.vo.Dog;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yhl
 * @date 2019/4/22
 */
@Component
@Service(version = "1.0")
public class DogServiceImpl implements DogService {
    private static int count = 0;
    @Override
    public List<Dog> selectDogs() throws InterruptedException {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("wangcai","zangsan"));
        System.out.println(count++);
        TimeUnit.SECONDS.sleep(10);
        return dogs;
    }

    @Override
    public List<Dog> selectDogCache(String name) {
        return null;
    }

    @Override
    public List<Dog> selectDogCache(String name, String owner) {
        return null;
    }
}
