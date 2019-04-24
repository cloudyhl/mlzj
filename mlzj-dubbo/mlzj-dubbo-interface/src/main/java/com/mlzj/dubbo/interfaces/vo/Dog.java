package com.mlzj.dubbo.interfaces.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/4/22
 */
@Data
@AllArgsConstructor
public class Dog implements Serializable {

    private static final long serialVersionUID = 3341028161164752227L;
    /**
     * 名字
     */
    private String name;

    /**
     * 所属人
     */
    private String ownerName;
}

