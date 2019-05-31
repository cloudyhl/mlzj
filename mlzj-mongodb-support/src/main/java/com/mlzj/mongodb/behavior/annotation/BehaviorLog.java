package com.mlzj.mongodb.behavior.annotation;

import java.lang.annotation.*;

/**
 * 接口访问日志
 * @author yhl
 * @date 2019/5/24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BehaviorLog {



    /**
     * 模块名称
     * @return 模块名称
     */
    String appName();

    /**
     * 访问路径
     * @return 访问路径
     */
    String value();

    /**
     * 接口名称,或根据具体场景统一菜单或按钮名称
     * @return 返回接口或菜单名称
     */
    String actionName();

}
