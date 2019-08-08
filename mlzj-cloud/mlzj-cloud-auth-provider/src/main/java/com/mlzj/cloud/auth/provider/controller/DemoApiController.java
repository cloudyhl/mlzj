package com.mlzj.cloud.auth.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2019/7/26
 */
@RestController
@RequestMapping("/api")
public class DemoApiController {

    @RequestMapping("/message")
    public String getApiMessage(){
        return "this is a api controller show";
    }

}
