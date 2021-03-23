package com.mlzj.common.annotation;

import java.lang.annotation.*;

/**
 * 表示这个方法需要做参数检验
 * @author yhl
 * @date 2020/6/3
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validation {
}
