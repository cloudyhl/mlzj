package com.mlzj.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * mlzj-cloud配置中心
 * @author yhl
 * @date 2019/6/1
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class MlzjCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudConfigApplication.class, args);
    }

}
