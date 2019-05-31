package com.mlzj.commontest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.commontest.dao.OrderDao;
import com.mlzj.commontest.model.Order;
import com.mlzj.commontest.model.User;
import com.mlzj.commontest.service.OrderService;
import com.mlzj.commontest.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Component
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {


    @Resource
    private UserService userService;

    @Override
    public void saveOrder(Order order) {
        super.save(order);
    }


    private void savesOrder(Order order) {
        super.save(order);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUsTransaction(){
        User user = new User();
        user.setUserName("zhangsan");
        user.setAge(10);
        user.setAddress("abs");
        userService.saveUser(user);
        Order order = new Order();
        order.setOrderBn("1");
        order.setComment("123456");
        this.savesOrder(order);

    }
}
