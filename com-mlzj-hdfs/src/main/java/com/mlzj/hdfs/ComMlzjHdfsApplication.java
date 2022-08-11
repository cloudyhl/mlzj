package com.mlzj.hdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@ComponentScan(basePackages = {"com.mlzj", "bio.nvwa.boot.hdfs"})
public class ComMlzjHdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComMlzjHdfsApplication.class, args);
    }

}
