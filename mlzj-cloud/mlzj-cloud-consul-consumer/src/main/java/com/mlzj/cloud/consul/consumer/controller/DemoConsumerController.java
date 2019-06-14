package com.mlzj.cloud.consul.consumer.controller;

import com.mlzj.cloud.feign.consul.ConsulProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 基本consul消费controller
 * @author yhl
 * @date 2019//6/12
 */
@RestController
@RefreshScope
public class DemoConsumerController {

    @Resource
    private ConsulProviderService consulProviderService;

    @Value("${mlzj.consul.message}")
    private String message;

    @Resource
    private Properties properties;

    @GetMapping("/getConsul")
    public String getConsulProviderString(){
        System.out.println(message);
        System.out.println(consulProviderService.selectConsul());
        properties.getMessage();
        return consulProviderService.selectConsul();
    }


}
