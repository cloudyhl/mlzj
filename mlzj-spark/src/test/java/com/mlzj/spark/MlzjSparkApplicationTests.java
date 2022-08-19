package com.mlzj.spark;

import com.mlzj.spark.service.SparkTestService;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MlzjSparkApplicationTests {

    @Resource
    private SparkTestService sparkTestService;

    @Test
    void contextLoads() throws InterruptedException {
        sparkTestService.calculateTopTen();
    }

}
