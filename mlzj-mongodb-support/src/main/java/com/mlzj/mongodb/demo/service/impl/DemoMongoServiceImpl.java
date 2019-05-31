package com.mlzj.mongodb.demo.service.impl;

import com.mlzj.mongodb.demo.dao.MongoDemoDao;
import com.mlzj.mongodb.demo.model.Mongo;
import com.mlzj.mongodb.demo.service.DemoMongoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/5/23
 */
//@Service
public class DemoMongoServiceImpl implements DemoMongoService {

    @Resource
    private MongoDemoDao mongoDemoDao;

    @Override
    public void saveMongo(Mongo mongo) {
        mongoDemoDao.save(mongo);
    }

    @Override
    public Mongo selectMongoByColor(String color) {
        return mongoDemoDao.findMongoByColor(color);
    }
}
