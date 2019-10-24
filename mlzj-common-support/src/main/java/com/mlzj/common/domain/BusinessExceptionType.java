package com.mlzj.common.domain;

/**
 * @author yhl
 * @date 2019/3/26
 */
public interface BusinessExceptionType {
    /**
     * 获取状态码
     * @return 状态码
     */
    String getCode();

    /**
     *错误信息
     * @return 错误信息
     */
    String getDescribe();
}
