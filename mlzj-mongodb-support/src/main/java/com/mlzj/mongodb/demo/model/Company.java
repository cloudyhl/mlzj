package com.mlzj.mongodb.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试使用所属公司
 * @author yhl
 * @date 2019/5/27
 */
@Data
public class Company implements Serializable {
    private static final long serialVersionUID = 4826771977125341651L;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司电话
     */
    private String companyTel;
}
