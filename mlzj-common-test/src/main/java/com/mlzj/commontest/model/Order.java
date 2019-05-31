package com.mlzj.commontest.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Data
@TableName("mlzj_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 3315390313246865232L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderBn;

    private String comment;
}
