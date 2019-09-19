package com.mlzj.commontest.observe;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yhl
 * @date 2019/9/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventB extends MlzjEvent {

    private int x;

    private int y;

    public EventB(Subject subject,int x, int y) {
        super(subject);
        this.x = x;
        this.y = y;
    }
}
