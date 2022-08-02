package com.mlzj.activity.handler;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;


public class ActivityHandler implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println(1);
    }
}
