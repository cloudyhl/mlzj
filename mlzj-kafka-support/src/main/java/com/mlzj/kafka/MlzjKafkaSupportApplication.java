package com.mlzj.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author yhl
 * @date 2019/4/24
 */
@SpringBootApplication
@EnableKafka
public class MlzjKafkaSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjKafkaSupportApplication.class, args);
    }

}
