package com.mlzj.cloud.feign.service.order;

import com.mlzj.cloud.feign.fallback.OrderServiceFallbackImpl;
import com.mlzj.cloud.feign.model.order.SimpleOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 订单service
 * @author yhl
 * @date 2019/6/2
 */
@FeignClient(name = "mlzj-order",fallback = OrderServiceFallbackImpl.class)
public interface OrderService {


    /**
     * 保存订单
     * @param simpleOrder 简单对象模型

     */
    @RequestMapping(value = "/orderApi/add",method = RequestMethod.POST)
    void saveOrder(@RequestBody SimpleOrder simpleOrder);

    /**
     * 秀
     * @param name 名字
     * @param age 年龄
     */
    @RequestMapping(value = "/orderApi/show",method = RequestMethod.GET)
    void show(@RequestParam("name") String name, @RequestParam("age") String age);

    /**
     * 输出参数集合
     * @param hashMap 参数集合
     */
    @RequestMapping(value = "/orderApi/map",method = RequestMethod.POST)
    void showMap(@RequestBody Map<Integer, SimpleOrder> hashMap);
}
