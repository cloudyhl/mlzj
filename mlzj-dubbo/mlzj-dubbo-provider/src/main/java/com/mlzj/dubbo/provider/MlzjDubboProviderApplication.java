package com.mlzj.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yhl
 * @date 2019/4/16
 */
@SpringBootApplication
//@ImportResource(locations = {"classpath:application-dubbo.xml"})
public class MlzjDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjDubboProviderApplication.class, args);
    }

}
