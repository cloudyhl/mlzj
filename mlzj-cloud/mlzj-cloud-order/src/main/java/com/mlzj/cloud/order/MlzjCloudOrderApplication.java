package com.mlzj.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 订单节点1
 * @author yhl
 * @date 2019/6/1
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.mlzj.cloud")
@EnableCircuitBreaker
@ComponentScan("com.mlzj.cloud")
public class MlzjCloudOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudOrderApplication.class, args);
    }

}
