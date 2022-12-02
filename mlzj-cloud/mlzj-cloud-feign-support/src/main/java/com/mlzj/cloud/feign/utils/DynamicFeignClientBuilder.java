package com.mlzj.cloud.feign.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DynamicFeignClientBuilder {

    FeignClientBuilder feignClientBuilder;

    public DynamicFeignClientBuilder(@Autowired ApplicationContext appContext) {
        this.feignClientBuilder = new FeignClientBuilder(appContext);
    }

    public DynamicFeignClient getFeignClient(String serviceName) {
       return this.feignClientBuilder.forType(DynamicFeignClient.class, serviceName).build();
    }

}
