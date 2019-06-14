package com.mlzj.cloud.zuul.filter;

/**
 * 笔记
 * @author yhl
 * @date 2019/6/8
 */
public interface Note {

    /**
     * zuul中一共有四种不同生命周期的filter
     * 1pre 在zuul路由到下游服务之前执行，可对请求进行预处理
     * 2route 是zuul路由动作的执行者
     * 3post 源服务返回结果，或者返回异常信息后执行的
     * 4error 整个周期发生异常就会进入，全局异常处理
     */

}
