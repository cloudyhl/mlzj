package com.mlzj.common.annotation;

import java.lang.annotation.*;

/**
 * 用于判断是否需要校验
 * @author yhl
 * @date 2020/6/3
 */
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsNeedCheck {

    /**
     * 用于返回校验的类型
     * @return 校验类型
     */
    String type() default "";

    /**
     * 是否基本校验
     * @return 是否基本校验
     */
    boolean isSimpleCheck() default true;
}
