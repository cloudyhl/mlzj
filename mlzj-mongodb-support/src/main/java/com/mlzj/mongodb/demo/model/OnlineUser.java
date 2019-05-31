package com.mlzj.mongodb.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/5/24
 */
@Data
public class OnlineUser implements Serializable {

    private static final long serialVersionUID = 3736595653811990902L;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户等级
     */
    private String level;
}
