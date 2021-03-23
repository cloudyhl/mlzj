package com.mlzj.commontest.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yhl
 * @date 2020/5/11
 */
@Data
@TableName(value = "order_item")
public class OrderItemBean {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderBn;

    private Integer price;
}
