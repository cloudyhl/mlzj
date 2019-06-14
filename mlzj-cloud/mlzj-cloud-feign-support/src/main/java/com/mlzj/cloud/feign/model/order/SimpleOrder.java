package com.mlzj.cloud.feign.model.order;

import lombok.Data;

import java.io.Serializable;

/**
 * 简单的订单对象
 * @author yhl
 * @date 2019/6/2
 */
@Data
public class SimpleOrder implements Serializable {

    private static final long serialVersionUID = -4790767680254611300L;

    private String orderBn;

    private String address;

    private String logisticsBn;
}
