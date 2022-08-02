package com.mlzj.activity;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
public class MlzjActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjActivityApplication.class, args);
    }

}
