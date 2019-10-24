package com.mlzj.common.domain;

/**
 * @author yhl
 * @date 2019/8/29
 */
public interface ExceptionType {

    /**
     * 获取错误码
     * @return 错误码
     */
    String getCode();

    /**
     * 获取错误描述 错误描述
     * @return 错误描述
     */
    String getDescription();

}
