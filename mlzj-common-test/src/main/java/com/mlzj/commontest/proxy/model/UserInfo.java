package com.mlzj.commontest.proxy.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/5/29
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 3050997961684926699L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
