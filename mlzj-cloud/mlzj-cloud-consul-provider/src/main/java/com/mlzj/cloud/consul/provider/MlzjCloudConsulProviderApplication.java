package com.mlzj.cloud.consul.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * consul 功能提供者
 * @author yhl
 * @date 2019/6/12
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MlzjCloudConsulProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudConsulProviderApplication.class, args);
    }

}
