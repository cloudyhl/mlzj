package com.mlzj.commontest.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestEvent extends ApplicationEvent {

    private static final long serialVersionUID = -3778713921888226869L;
    private String eventName;

    private String eventString;

    public TestEvent(Object source, String eventName, String eventString) {
        super(source);
        this.eventName = eventName;
        this.eventString = eventString;
    }
}
