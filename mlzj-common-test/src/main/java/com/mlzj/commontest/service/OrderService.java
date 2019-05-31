package com.mlzj.commontest.service;

import com.mlzj.commontest.model.Order;

/**
 * @author yhl
 * @date 2019/4/26
 */
public interface OrderService {

    /**
     * 保存订单
     * @param order 订单
     */
    void saveOrder(Order order);

    /**
     * 保存使用事务
     */
    void saveUsTransaction();
}
