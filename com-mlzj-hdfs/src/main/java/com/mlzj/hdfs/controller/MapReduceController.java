package com.mlzj.hdfs.controller;

import com.mlzj.hdfs.service.MapReduceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhl
 * @date 2022/8/12
 */
@Api(value = "/mapReduce", tags = "mapReduce")
@Slf4j
@RestController
@RequestMapping("/mapReduce")
public class MapReduceController {

    @Resource
    private MapReduceService mapReduceService;


    @ApiOperation(httpMethod = "POST", value = "创建文件夹")
    @RequestMapping(value = "/mkdirFolder", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String mkdirFolder(
            @RequestBody @ApiParam(name = "JSON对象", value = "json格式对象", required = true) String path) throws InterruptedException, IOException, ClassNotFoundException {
        mapReduceService.startJob(path);
        return "OK";

    }

}
