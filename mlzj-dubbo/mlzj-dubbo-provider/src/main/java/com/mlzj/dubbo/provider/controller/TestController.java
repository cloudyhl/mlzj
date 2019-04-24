package com.mlzj.dubbo.provider.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlzj.dubbo.interfaces.service.animal.DogService;
import com.mlzj.dubbo.interfaces.vo.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yhl
 * @date 2019/4/22
 */
@Controller
@RequestMapping("/provider")
public class TestController {

    @Reference(interfaceClass = DogService.class,version = "1.1",cache = "lru")
    private DogService dogService;

    @ResponseBody
    @GetMapping("/selectDog")
    public List<Dog> selectAllDog() throws InterruptedException {
        return dogService.selectDogs();

    }

    @ResponseBody
    @RequestMapping("/selectDog1")
    public List<Dog> selectDogCache(){
        return dogService.selectDogCache("zhangsan","zhaosi");
    }


    @ResponseBody
    @RequestMapping("/selectDogCacheTest")
    public List<Dog> selectDogCacheTest(){
        return dogService.selectDogCache("zhangsan","wangwu");
    }

}
