package com.mlzj.cloud.order.feign;

import com.mlzj.cloud.feign.model.order.SimpleOrder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单feign实现
 * @author yhl
 * @date 2019/6/2
 */
@RestController
@RequestMapping("orderApi")
public class OrderFeignServiceImpl {

    @PostMapping("/add")
    public void saveOrder(@RequestBody SimpleOrder simpleOrder){
        System.out.println(simpleOrder);
    }
    @GetMapping("/show")
    public void show(String name,String age){
        System.out.println("-----------------------show--------------------------");
        System.out.println(name);
        System.out.println(age);
    }
    @PostMapping(value = "/map")
    public void showMap(@RequestBody Map<Integer,SimpleOrder> hashMap){
        hashMap.forEach((x,y)->{

            System.out.println(x);
            System.out.println(y);
        });
    }

}
