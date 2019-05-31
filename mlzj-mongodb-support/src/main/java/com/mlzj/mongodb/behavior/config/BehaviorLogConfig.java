package com.mlzj.mongodb.behavior.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author yhl
 * @date 2019/5/28
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.mlzj.mongodb")
@ComponentScan("com.mlzj.mongodb.behavior")
public class BehaviorLogConfig {

}
