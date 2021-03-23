package com.mlzj.common.validate;

/**
 * @author yhl
 * @date 2020/6/3
 */
public interface Validation {

    /**
     * 校验
     * @param obj 需要校验的参数
     * @return 是否检验成功
     */
    boolean validate(Object obj);

}
