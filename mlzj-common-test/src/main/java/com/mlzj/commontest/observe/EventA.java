package com.mlzj.commontest.observe;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yhl
 * @date 2019/9/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EventA extends MlzjEvent {

    private String name;

    private String year;

    public EventA(Subject subject,String name,String year) {
        super(subject);
        this.name = name;
        this.year = year;
    }
}
