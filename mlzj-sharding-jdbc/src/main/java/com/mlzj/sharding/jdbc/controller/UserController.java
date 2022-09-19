package com.mlzj.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlzj.common.utils.IdGeneratorSnowflake;
import com.mlzj.sharding.jdbc.entity.TUser;
import com.mlzj.sharding.jdbc.entity.User;
import com.mlzj.sharding.jdbc.service.TUserService;
import com.mlzj.sharding.jdbc.service.UserService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2022/9/15
 */
@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService tUserService;

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody User tUser) {
        tUser.setId(IdGeneratorSnowflake.get().nextId());
        tUser.setName(tUser.getName());
        tUserService.save(tUser);
    }

    @GetMapping("/selectUser")
    public IPage<User> selectPage(@RequestParam Long page, @RequestParam Long size) {
        return tUserService.page(new Page<>(page, size));
    }

    @GetMapping("/getById")
    public User getById(@RequestParam Long id) {
        return tUserService.getById(id);
    }

    @GetMapping("/getByName")
    public User getByName(@RequestParam String name) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getName, name);
        return tUserService.getOne(queryWrapper);
    }
}
