package com.mlzj.hbase.controller;

import com.mlzj.hbase.dto.UserEntity;
import com.mlzj.hbase.service.HbaseService;
import com.mlzj.hbase.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
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
 * @date 2022/8/18
 */
@Api
@Slf4j
@RestController
@RequestMapping("/hbase")
public class HbaseController {

    @Resource
    private HbaseService hbaseService;

    @Resource
    private UserService userService;

    @ApiOperation(value = "增加用户")
    @PostMapping("/insertUser")
    public void insertUser(@RequestBody UserEntity userEntity) {
        userService.insertUser(userEntity);
    }


    @ApiOperation(value = "创建表")
    @GetMapping("/createTable")
    public void createTable() throws IOException {
        hbaseService.createTable();
    }

    @ApiOperation(value = "根据查询rowKey查询结果")
    @GetMapping("/findByRowKey")
    public UserEntity findByRowKey(@RequestParam String rowKey) throws IOException {
        return hbaseService.findByRowKey(rowKey);
    }

    @ApiOperation(value = "查询所有数据")
    @GetMapping("/findAll")
    public List<UserEntity> findAll() throws IOException {
        return hbaseService.findAll();
    }

    @ApiOperation(value = "根据用户名查询")
    @GetMapping("/findByName")
    public List<UserEntity> findByName(@RequestParam String name) throws IOException {
        return hbaseService.findUserByPrefixName(name);
    }


    @ApiOperation(value = "mybatis查询所有数据")
    @GetMapping("/getAll")
    public List<UserEntity> getAll() throws IOException {
        List<UserEntity> list = userService.list();
        return userService.list();
    }
}
