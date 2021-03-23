package com.mlzj.commontest;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yhl
 * @date 2019/4/26
 */
@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = {"com.mlzj.commontest.dao"})
@EnableTransactionManagement
//@ComponentScan({"com.mlzj.mongodb","com.mlzj.commontest"})
public class MlzjCommonTestApplication {


    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }

    public static void main(String[] args) {
        SpringApplication.run(MlzjCommonTestApplication.class, args);
    }

}
