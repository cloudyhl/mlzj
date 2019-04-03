package com.mlzj.rocketmq;

import com.mlzj.rocketmq.annotation.EnableMlzjRocketMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yhl
 * @date 2019/3/26
 */
@SpringBootApplication
@EnableMlzjRocketMq
public class MlzjRocketMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MlzjRocketMqApplication.class);
    }
}
