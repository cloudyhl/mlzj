package com.mlzj.common.utils;

import com.mlzj.common.constants.CommonConstants;
import com.mlzj.common.domain.TwoParamFunction;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 重试工具类
 * @author yhl
 * @date 2020/7/10
 */
@Slf4j
public class RetryUtils {

    private RetryUtils(){}

    /**
     * 重试机制一共调用四次
     *
     * @param supplier 方法
     * @param <T>      返回的值
     * @return 方法的返回值
     */
    public static <T> T getAndRetry(Supplier<T> supplier) {
        for (int index = 0; index < CommonConstants.DEFAULT_RETRY_TIMES; index++) {
            T result = supplier.get();
            if (result != null) {
                return result;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                log.error("线程休眠发生异常:", e);
            }
        }
        return supplier.get();
    }

    /**
     * 重试机制一共调用四次
     *
     * @param twoParamFunction 方法
     * @param <T>      返回的值
     * @return 方法的返回值
     */
    public static <O, T, R> R getAndRetry(TwoParamFunction<O, T, R> twoParamFunction, O param1, T param2) {
        for (int index = 0; index < CommonConstants.DEFAULT_RETRY_TIMES; index++) {
            R result = twoParamFunction.execute(param1, param2);
            if (result != null) {
                return result;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                log.error("线程休眠发生异常:", e);
            }
        }
        return twoParamFunction.execute(param1, param2);
    }
}
