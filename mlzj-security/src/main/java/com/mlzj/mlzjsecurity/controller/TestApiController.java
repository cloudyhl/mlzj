package com.mlzj.mlzjsecurity.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author yhl
 * @date 2019/1/25
 */
@RestController
@RequestMapping("/api")
public class TestApiController {

    @GetMapping("/order/{id}")
    @ResponseBody
    public String getOrder(@PathVariable String id){
        return "order"+id;
    }
}
