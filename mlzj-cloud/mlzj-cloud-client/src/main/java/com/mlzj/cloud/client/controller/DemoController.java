package com.mlzj.cloud.client.controller;


import com.google.common.collect.Maps;
import com.mlzj.cloud.client.common.MlzjConfigProperties;
import com.mlzj.cloud.feign.model.order.SimpleOrder;
import com.mlzj.cloud.feign.service.order.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 测试controller
 * @author yhl
 * @date 2019/6/2
 */
@RestController
@RefreshScope
public class DemoController {

    @Resource
    private OrderService orderService;

    @Value("${mlzj.client.message}")
    private String message;

    @Resource
    private MlzjConfigProperties mlzjConfigProperties;

    @GetMapping("/addUser")
    public String testFeign(HttpServletRequest request){
        SimpleOrder simpleOrder = new SimpleOrder();
        simpleOrder.setAddress("四川");
        simpleOrder.setLogisticsBn("123213221");
        simpleOrder.setOrderBn("1231312");
        orderService.show("zhang","10");
        orderService.saveOrder(simpleOrder);
        Map<Integer,SimpleOrder> hashMap = Maps.newHashMap();
        hashMap.put(1,simpleOrder);
        hashMap.put(2,simpleOrder);
        orderService.showMap(hashMap);
        return "OK";
    }

    @GetMapping("/ignore")
    public String ignore(){
        return "ignore";
    }

    @GetMapping("/config")
    public String config(){
        System.out.println(message);
        return mlzjConfigProperties.getMessage();
    }

    @GetMapping
    public String active(HttpServletRequest request){
        String prefix = request.getHeader("prefix");
        String username = request.getParameter("username");
        System.out.println(username);
        System.out.println(prefix);
        return "active";
    }
}
