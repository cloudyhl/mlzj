package com.mlzj.common.domain;

/**
 * @author yhl
 * @date 2020/7/10
 */
@FunctionalInterface
public interface TwoParamFunction<O, T, R> {

    /**
     * 执行
     *
     * @param param1 参数1
     * @param param2 参数2
     * @return 返回结果
     */
    R execute(O param1, T param2);
}
