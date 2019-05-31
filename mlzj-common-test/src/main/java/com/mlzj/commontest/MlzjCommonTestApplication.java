package com.mlzj.commontest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yhl
 * @date 2019/4/26
 */
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
//@ComponentScan({"com.mlzj.mongodb","com.mlzj.commontest"})
public class MlzjCommonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCommonTestApplication.class, args);
    }

}
