package com.mlzj.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * mlzj cloud gateway
 * @author yhl
 * @date 2019/6/13
 */
@SpringBootApplication
@EnableEurekaClient
public class MlzjCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudGatewayApplication.class, args);
    }

}
