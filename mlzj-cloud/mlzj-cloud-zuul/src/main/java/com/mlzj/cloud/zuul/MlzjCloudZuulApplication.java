package com.mlzj.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**mlzj-cloud-zuul
 * @author yhl
 * @date 2019/6/5
 */
@SpringBootApplication
@EnableZuulProxy
public class MlzjCloudZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudZuulApplication.class, args);
    }

}
