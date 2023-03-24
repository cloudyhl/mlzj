package com.mlzj.alibaba.client.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 */
@RestController("/test")
@Api
public class TestController {

    @ApiOperation("测试")
    @PostMapping("/test")
    public User test(@RequestBody User user){
        return null;
    }

}
