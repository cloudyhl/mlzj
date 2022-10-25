package com.mlzj.oauth2.zuul.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2022/9/22
 */
@Api
@RestController
public class TestController {

    @GetMapping("/api/success")
    public String apiSuccess() {
        return "success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

}
