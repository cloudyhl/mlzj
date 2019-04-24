package com.mlzj.dubbo.provider.config;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yhl
 * @date 2019/4/16
 */
@Configuration
public class DubboConfig {

    @Bean
    @ConfigurationProperties(prefix="dubbo.application")
    public ApplicationConfig applicationConfig() {
        return new ApplicationConfig();
    }

    @Bean
    @ConfigurationProperties(prefix="dubbo.registry")
    public RegistryConfig registryConfig() {
        return new RegistryConfig();
    }

    @Bean
    @ConfigurationProperties(prefix="dubbo.annotation")
    public AnnotationBean annotationBean() {
        return new AnnotationBean();
    }

    @Bean
    @ConfigurationProperties(prefix="dubbo.protocol")
    public ProtocolConfig protocolConfig() {
        return new ProtocolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.consumer")
    public ConsumerConfig consumerConfig(){
        return new ConsumerConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "dubbo.provider")
    public ProviderConfig providerConfig(){
        return new ProviderConfig();
    }
}

