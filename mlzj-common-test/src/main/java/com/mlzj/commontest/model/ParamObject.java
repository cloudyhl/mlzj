package com.mlzj.commontest.model;

import lombok.Data;

/**
 * 参数对象
 * @author yhl
 * @date 2019/9/19
 */
@Data
public class ParamObject<T>{


    T value;

    public ParamObject(T value){
        this.value = value;
    }
}
