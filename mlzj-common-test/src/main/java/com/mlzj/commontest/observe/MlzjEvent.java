package com.mlzj.commontest.observe;

/**
 * @author yhl
 * @date 2019/9/17
 */
public class MlzjEvent {

    private Subject subject;

    public MlzjEvent(Subject subject){
        this.subject = subject;
        this.subject.addListener(this);
    }

}
