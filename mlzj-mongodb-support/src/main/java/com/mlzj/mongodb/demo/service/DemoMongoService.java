package com.mlzj.mongodb.demo.service;

import com.mlzj.mongodb.demo.model.Mongo;

/**
 * @author yhl
 * @date 2019/5/23
 */
public interface DemoMongoService {

    /**
     * 保存一个mongo
     * @param mongo mongo
     */
    void saveMongo(Mongo mongo);

    /**
     * 根据颜色返回
     * @param color 颜色
     * @return mongo
     */
    Mongo selectMongoByColor(String color);
}
