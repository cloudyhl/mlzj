package com.mlzj.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yhl
 * @date 2019/3/28
 */
@SpringBootApplication
@EnableTransactionManagement
public class MlzjRedisSupportApplication {


    public static void main(String[] args) {
        SpringApplication.run(MlzjRedisSupportApplication.class, args);
    }

}
