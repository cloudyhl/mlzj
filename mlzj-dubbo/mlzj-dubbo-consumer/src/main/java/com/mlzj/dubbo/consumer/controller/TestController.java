package com.mlzj.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mlzj.dubbo.interfaces.service.user.UserService;
import com.mlzj.dubbo.interfaces.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yhl
 * @date 2019/4/22
 */
@Controller
@RequestMapping("/consumer")
public class TestController {

    @Reference(interfaceClass = UserService.class)
    private UserService userService;

    @ResponseBody
    @GetMapping("/selectDog")
    public List<User> selectAllDogs() {
        return userService.selectUser();
    }

}
