package com.mlzj.redis.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/3/29
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 376921311595456693L;
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}
