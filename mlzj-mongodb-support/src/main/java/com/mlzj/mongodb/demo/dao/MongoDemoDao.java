package com.mlzj.mongodb.demo.dao;

import com.mlzj.mongodb.demo.model.Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yhl
 * @date 2019/5/23
 */
public interface MongoDemoDao extends MongoRepository<Mongo,Long> {

    /**
     * 查询mongo
     * @param color 颜色
     * @return mongo
     */
    Mongo findMongoByColor(String color);

}
