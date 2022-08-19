package com.mlzj.spark.controller;

import com.alibaba.fastjson.JSONObject;
import com.mlzj.spark.service.SparkTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2022/8/16
 */
@Api(value = "spark测试Controller")
@Slf4j
@RestController
@RequestMapping("/spark")
public class SparkTestController {

    @Resource
    private SparkTestService sparkTestService;


    @ApiOperation(value = "test")
    @GetMapping("/test")
    public void test() throws InterruptedException {
        sparkTestService.calculateTopTen();
    }

}
