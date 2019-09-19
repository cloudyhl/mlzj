package com.mlzj.commontest.observe;

import java.util.function.Consumer;

/**
 * @author yhl
 * @date 2019/9/17
 */
public class EventBListener implements MlzjEventListener<EventB>, Consumer<Integer> {
    @Override
    public void onMlzjEvent(EventB mlzjEvent) {
        System.out.println(mlzjEvent.getX()+mlzjEvent.getY());
    }

    @Override
    public void accept(Integer integer) {

    }
}
