package com.mlzj.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * mlzj  断路器监控仪表盘
 * @author yhl
 * @date 2019/6/3
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine
@EnableEurekaClient
public class MlzjCloudHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudHystrixDashboardApplication.class, args);
    }

}
