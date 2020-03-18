package com.mlzj.commontest.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yhl
 * @date 2019/9/16
 */
@Data
public class UserInfo implements Serializable{
    private static final long serialVersionUID = -7568367749024256599L;

    private String userName;

    private Integer age;

    private Dog dog;

    private List<Order> orders;


}
