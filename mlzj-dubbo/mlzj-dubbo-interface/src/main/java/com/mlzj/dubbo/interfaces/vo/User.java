package com.mlzj.dubbo.interfaces.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/4/16
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 7670236826236755998L;

    private String userName;

    private int age;

    private String address;

    public User() {
    }
}
