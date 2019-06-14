package com.mlzj.cloud.order.controller;

import com.mlzj.cloud.feign.service.client.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/6/3
 */
@RestController
public class ClientController {

    @Resource
    private ClientService clientService;

    @GetMapping("/select")
    public String selectUser(){
        System.out.println("---------select client user-------------");
        return clientService.selectUser();
    }
}
