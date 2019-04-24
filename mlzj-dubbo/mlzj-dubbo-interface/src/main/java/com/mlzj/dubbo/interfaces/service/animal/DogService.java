package com.mlzj.dubbo.interfaces.service.animal;

import com.mlzj.dubbo.interfaces.vo.Dog;

import java.util.List;

/**
 * @author yhl
 * @date 2019/4/22
 */
public interface DogService {

    /**
     * 查询所有的狗
     * @return 所有的狗
     */
    List<Dog> selectDogs() throws InterruptedException;

    /**
     * 结果缓存测试
     * @param name 名字
     * @return 返回
     */
    List<Dog> selectDogCache(String name);

    /**
     * 结果缓存测试
     * @param name 名字
     * @param owner 所有者
     * @return 返回
     */
    List<Dog> selectDogCache(String name,String owner);
}
