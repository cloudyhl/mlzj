package com.mlzj.commontest.observe;

/**
 * @author yhl
 * @date 2019/9/17
 */
public interface MlzjEventListener<T extends MlzjEvent> {

    /**
     * 监听事件
     * @param mlzjEvent 监听事件
     */
    void onMlzjEvent(T mlzjEvent);

}
