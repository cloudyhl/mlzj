package com.mlzj.mlzjsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yhl
 * @date 2018/12/12
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    private static final String LOGIN = "login";

    private static final String LIST = "/user/list";

    private static final String INDEX = "index";

    @GetMapping("/login")
    public String login(){
        return LOGIN;
    }

    @GetMapping("/list")
    @PreAuthorize("hasPermission('user','query')")
    public String list(){
        return LIST;
    }


    @PostMapping("/loginSuccess")
    public String index(){
        return INDEX;
    }
}
