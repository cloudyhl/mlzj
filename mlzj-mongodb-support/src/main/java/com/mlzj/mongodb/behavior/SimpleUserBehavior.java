package com.mlzj.mongodb.behavior;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yhl
 * @date 2019/5/24
 */
@Data
public class SimpleUserBehavior implements Serializable {

    private static final long serialVersionUID = -6164339062307259912L;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 补充信息
     */
    private Object supplement;

    /**
     * 模块名称
     */
    private String appName;

    /**
     * 访问路径
     */
    private String value;

    /**
     * 接口名称,或根据具体场景统一菜单或按钮名称
     */
    private String actionName;

    /**
     * 操作日期
     */
    private String date;
}
