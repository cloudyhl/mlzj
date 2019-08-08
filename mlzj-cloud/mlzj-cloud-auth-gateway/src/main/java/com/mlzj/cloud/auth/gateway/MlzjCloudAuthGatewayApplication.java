package com.mlzj.cloud.auth.gateway;

import com.mlzj.cloud.auth.gateway.config.SecurityConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author yhl
 * @date 2019/7/26
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableConfigurationProperties({SecurityConfigProperties.class})
public class MlzjCloudAuthGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MlzjCloudAuthGatewayApplication.class, args);
    }

}
