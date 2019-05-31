package com.mlzj.commontest.controller;

import com.mlzj.mongodb.behavior.annotation.BehaviorLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2019/5/27
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/hello")
    @BehaviorLog(appName = "mlzj-test",value = "test接口",actionName = "hello")
    public void hello(){
        System.out.println("hello");
    }

}
