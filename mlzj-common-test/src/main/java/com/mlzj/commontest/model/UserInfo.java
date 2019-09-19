package com.mlzj.commontest.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/9/16
 */
@Data
public class UserInfo implements Serializable, Cloneable {
    private static final long serialVersionUID = -7568367749024256599L;

    private String userName;

    private Integer age;

    private Dog dog;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setDog(Dog dog) throws CloneNotSupportedException {
        this.dog = (Dog) dog.clone();
    }
}
