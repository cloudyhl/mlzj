package com.mlzj.sharding.jdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mlzj.common.utils.IdGeneratorSnowflake;
import com.mlzj.sharding.jdbc.entity.TUser;
import com.mlzj.sharding.jdbc.service.TUserService;
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
@RequestMapping("/user")
@RestController
@Slf4j
public class TUserController {

    @Resource
    private TUserService tUserService;

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody TUser tUser) {
        tUser.setId(IdGeneratorSnowflake.get().nextId());
        tUser.setName(tUser.getName());
        tUserService.save(tUser);
    }

    @GetMapping("/selectUser")
    public IPage<TUser> selectPage(@RequestParam Long page, @RequestParam Long size) {
        return tUserService.page(new Page<>(page, size));
    }

    @GetMapping("/getById")
    public TUser getById(@RequestParam Long id) {
        return tUserService.getById(id);
    }

    @GetMapping("/getByName")
    public TUser getByName(@RequestParam String name) {
        LambdaQueryWrapper<TUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TUser::getName, name);
        return tUserService.getOne(queryWrapper);
    }
}
