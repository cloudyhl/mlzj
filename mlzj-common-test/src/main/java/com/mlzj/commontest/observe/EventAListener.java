package com.mlzj.commontest.observe;

/**
 * @author yhl
 * @date 2019/9/17
 */
public class EventAListener implements MlzjEventListener<EventA>{


    @Override
    public void onMlzjEvent(EventA mlzjEvent) {
        System.out.println(mlzjEvent.getName()+mlzjEvent.getYear());
    }
}
