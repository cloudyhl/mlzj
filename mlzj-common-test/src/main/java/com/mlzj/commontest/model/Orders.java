package com.mlzj.commontest.model;

import lombok.Data;

import java.util.List;

/**
 * @author yhl
 * @date 2020/5/11
 */
@Data
public class Orders {

    private Integer id;

    private String orderBn;

    private String name;

    private List<OrderItemBean> orderItems;
}
