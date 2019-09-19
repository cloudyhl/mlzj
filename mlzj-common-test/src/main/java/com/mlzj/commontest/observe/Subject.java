package com.mlzj.commontest.observe;

/**
 * 主题类
 * @author yhl
 * @date 2019/9/17
 */
public interface Subject {

    /**
     * 发布事件
     * @param mlzjEvent 事件
     */
    void publishEvent(MlzjEvent mlzjEvent);

    /**
     * 增加新的监听
     * @param mlzjEvent 事件
     */
    void addListener(MlzjEvent mlzjEvent);

}
