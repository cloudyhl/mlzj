//package com.mlzj.cloud.order.feign;
//
//import com.mlzj.cloud.feign.model.order.SimpleOrder;
//import com.mlzj.cloud.feign.service.order.OrderService;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
///**
// * @author yhl
// * @date 2019/6/2
// */
//@RestController
//public class OrderServiceImpl implements OrderService {
//    @Override
//    public void saveOrder(SimpleOrder simpleOrder, String name) {
//        System.out.println(simpleOrder);
//        System.out.println(name);
//    }
//
//    @Override
//    public void show(String name, String age) {
//
//        System.out.println(name);
//        System.out.println(age);
//    }
//
//    @Override
//    public void showMap(Map<Integer, SimpleOrder> hashMap) {
//        hashMap.forEach((x,y)->{
//            System.out.println(x);
//            System.out.println(y);
//        });
//    }
//}
