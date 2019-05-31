package com.mlzj.mongodb.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/5/23
 */
@Data
public class Mongo implements Serializable {
    private static final long serialVersionUID = -8931674813531675028L;

    /**
     * 名字
     */
    private String name;

    /**
     * 颜色
     */
    private String color;

    /**
     * 重量
     */
    private Integer weight;
}
