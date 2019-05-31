package com.mlzj.commontest.service.impl;

import com.mlzj.commontest.event.TestEvent;
import com.mlzj.commontest.model.Order;
import com.mlzj.commontest.model.User;
import com.mlzj.commontest.service.OrderService;
import com.mlzj.commontest.service.UserService;
import com.mlzj.commontest.utils.EventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Service
public class DemoServiceImpl {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;
    @Resource
    private EventPublisher eventPublisher;
    @Transactional(rollbackFor = Exception.class)
    public void usTransactionPush(){
        User user = new User();
        user.setAddress("四川");
        user.setAge(10);
        user.setUserName("wangwu");
        userService.saveUser(user);
        userService.saveUser(user);
        eventPublisher.publisherEvent(new TestEvent(eventPublisher,"transaction","transaction is go"));
        Order order = new Order();
        //int i = 1/0;
        order.setOrderBn("123");
        order.setComment("good");
        orderService.saveOrder(order);
    }
}
