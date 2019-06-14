package com.mlzj.cloud.consul.provider.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * consul provider 实现类
 * @author yhl
 * @date 2019/6/12
 */
@RestController
@RequestMapping("apiConsul/provider")
public class ConsulProviderServiceImpl {

    @GetMapping("/select")
    public String select(){
        return "consul provider";
    }
}
