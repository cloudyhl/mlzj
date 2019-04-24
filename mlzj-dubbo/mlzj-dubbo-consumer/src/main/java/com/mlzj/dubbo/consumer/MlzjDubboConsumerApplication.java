package com.mlzj.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yhl
 * @date 2019/4/21
 */
@SpringBootApplication
//@ImportResource(locations= {"classpath:application-dubbo.xml"})
public class MlzjDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjDubboConsumerApplication.class, args);
    }

}
