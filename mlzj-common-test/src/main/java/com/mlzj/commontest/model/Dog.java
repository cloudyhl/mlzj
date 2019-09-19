package com.mlzj.commontest.model;

import lombok.Data;

/**
 * @author yhl
 * @date 2019/9/16
 */
@Data
public class Dog implements Cloneable{

    private String dogName;

    private Integer dogAge;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
