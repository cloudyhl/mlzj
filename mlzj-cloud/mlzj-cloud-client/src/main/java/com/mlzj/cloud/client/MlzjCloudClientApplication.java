package com.mlzj.cloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * mlzj cloud 调用方模拟
 * @author yhl
 * @date 2019/6/2
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.mlzj.cloud")
@EnableEurekaClient
@ComponentScan("com.mlzj.cloud")
@EnableCircuitBreaker
public class MlzjCloudClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudClientApplication.class, args);
    }

}
