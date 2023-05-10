package com.mlzj.alibaba.client.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 */
@RestController("/test")
@Api
public class TestController {

    @ApiOperation("测试")
    @GetMapping("/getHello")
    public String test(){
        return "你好啊";
    }

}
