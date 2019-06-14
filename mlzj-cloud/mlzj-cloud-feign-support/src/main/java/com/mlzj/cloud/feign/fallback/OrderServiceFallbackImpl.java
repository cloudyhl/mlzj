package com.mlzj.cloud.feign.fallback;

import com.mlzj.cloud.feign.model.order.SimpleOrder;
import com.mlzj.cloud.feign.service.order.OrderService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author yhl
 * @date 2019/6/3
 */
@Component
public class OrderServiceFallbackImpl implements OrderService {
    public void saveOrder(SimpleOrder simpleOrder) {
        System.out.println("save order fail");
    }

    public void show(String name, String age) {
        System.out.println("show is fail");
    }

    public void showMap(Map<Integer, SimpleOrder> hashMap) {
        System.out.println("showMap is fail");
    }
}
