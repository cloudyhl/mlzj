package com.mlzj.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * springCloud eureka
 * @author yhl
 * @date 2019/5/31
 */
@SpringBootApplication
@EnableEurekaServer
public class MlzjCloudEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudEurekaApplication.class, args);
    }

}
