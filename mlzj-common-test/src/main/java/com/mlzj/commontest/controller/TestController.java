package com.mlzj.commontest.controller;

import com.mlzj.commontest.annotation.SayOut;
import com.mlzj.commontest.model.Mobile;
import com.mlzj.mongodb.behavior.annotation.BehaviorLog;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2019/5/27
 */
@RestController
@RequestMapping
@SayOut(value = "--------------gogogogogogo-----------------------")
public class TestController {

    @GetMapping("/hello")
    @BehaviorLog(appName = "mlzj-test",value = "test接口",actionName = "hello")
    public void hello(){
        System.out.println("hello");
    }

    @GetMapping("/mobile")
    public void mobile(Mobile mobile, BindingResult result){
        System.out.println(result);
        System.out.println(mobile);
    }

}
