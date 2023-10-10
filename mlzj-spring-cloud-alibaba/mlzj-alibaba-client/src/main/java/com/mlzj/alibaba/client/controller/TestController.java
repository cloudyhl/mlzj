package com.mlzj.alibaba.client.controller;


import com.mlzj.alibaba.client.dto.ClientReqDto;
import com.mlzj.alibaba.client.feign.ConsumerFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yhl
 */
@RestController("/test")
@Api
public class TestController {

    @Resource
    private ConsumerFeignClient consumerFeignClient;

    @ApiOperation("测试")
    @GetMapping("/getHello")
    public String test(){
        String consumer = consumerFeignClient.getConsumer("1");
        ClientReqDto clientReqDto = new ClientReqDto();
        clientReqDto.setId("112323123123213132");
        String s = consumerFeignClient.postConsumer(clientReqDto);
        System.out.println(s);
        System.out.println(consumer);
        return "你好啊";
    }

}
