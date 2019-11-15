package com.mlzj.commontest.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/11/14
 */
@Data
public class AModel implements Serializable {

    private String name;

    private BModel bModel;
}
