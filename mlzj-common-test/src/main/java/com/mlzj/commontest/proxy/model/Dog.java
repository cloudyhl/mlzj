package com.mlzj.commontest.proxy.model;

import com.mlzj.commontest.proxy.interfaces.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 狗
 * @author yhl
 * @date 2019/5/30
 */
@Data
@AllArgsConstructor
public class Dog implements Serializable, Animal {

    private static final long serialVersionUID = -5365823856503892994L;

    public Dog() {
    }

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    @Override
    public void say(){
        System.out.println("miao miao miao");
    }
}
