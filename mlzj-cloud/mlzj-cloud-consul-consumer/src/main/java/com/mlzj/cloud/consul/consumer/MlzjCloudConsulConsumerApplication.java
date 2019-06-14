package com.mlzj.cloud.consul.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * consul 功能消费者
 * @author yhl
 * @date 2019/6/12
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.mlzj.cloud")
@ComponentScan("com.mlzj.cloud")
public class MlzjCloudConsulConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudConsulConsumerApplication.class, args);
    }

}
