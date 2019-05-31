package com.mlzj.mongodb.demo.controller;

import com.mlzj.mongodb.behavior.annotation.BehaviorLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试访问日志
 * @author yhl
 * @date 2019/5/24
 */
//@RestController
//@RequestMapping("test")
public class DemoTestController {

    @GetMapping("hello")
    @BehaviorLog(appName = "mongo",value = "访问测试接口",actionName = "测试接口")
    public String hello(){
        return "hello";
    }

}
