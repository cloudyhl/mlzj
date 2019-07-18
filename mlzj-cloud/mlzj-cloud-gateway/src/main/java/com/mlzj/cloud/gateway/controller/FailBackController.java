package com.mlzj.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2019/6/14
 */
@RestController
public class FailBackController {

    @GetMapping("failBack")
    public String failBack(){
        return "this is fail back show message";
    }

}
