package com.mlzj.cloud.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author yhl
 * @date 2019/7/26
 */
@RestController
@RequestMapping
public class UserController {

    /**
     * 获取用户
     * @return 用户
     */
    @GetMapping("/user")
    public Principal getUser(Principal principal){
        return principal;
    }

}
