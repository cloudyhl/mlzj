package com.mlzj.oauth2.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class MlzjOauth2ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjOauth2ZuulApplication.class, args);
    }

}
