package com.mlzj.mongodb;

import com.mlzj.mongodb.demo.model.Mongo;
import com.mlzj.mongodb.demo.service.DemoMongoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MlzjMongodbSupportApplicationTests {

    @Resource
    private DemoMongoService demoMongoService;


    @Test
    public void contextLoads() {
    }

    @Test
    public void saveMongo(){
        Mongo mongo = new Mongo();
        mongo.setColor("green");
        mongo.setName("钱七");
        mongo.setWeight(2);
        demoMongoService.saveMongo(mongo);
    }

    @Test
    public void findMongo(){
        Mongo mongo = demoMongoService.selectMongoByColor("green");
        System.out.println(mongo);
    }

}
