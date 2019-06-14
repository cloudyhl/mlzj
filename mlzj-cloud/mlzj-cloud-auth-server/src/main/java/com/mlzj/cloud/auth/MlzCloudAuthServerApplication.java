package com.mlzj.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * mlzj 服务授权中心
 * @author yhl
 * @date 2019/6/8
 */
@SpringBootApplication
@EnableEurekaClient
public class MlzCloudAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzCloudAuthServerApplication.class, args);
    }

}
