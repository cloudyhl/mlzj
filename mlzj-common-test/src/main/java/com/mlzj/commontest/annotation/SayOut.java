package com.mlzj.commontest.annotation;

import java.lang.annotation.*;

/**
 * @author yhl
 * @date 2019/8/6
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SayOut {
    String value();
}
