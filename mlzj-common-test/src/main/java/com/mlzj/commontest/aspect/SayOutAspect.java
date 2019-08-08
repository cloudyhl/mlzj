package com.mlzj.commontest.aspect;

import com.mlzj.commontest.annotation.SayOut;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author yhl
 * @date 2019/8/6
 */
@Aspect
@Component
public class SayOutAspect {

    /**
     * within 使用类上的注解匹配
     */
    @Before(value = "@annotation(com.mlzj.commontest.annotation.SayOut)|| @within(com.mlzj.commontest.annotation.SayOut) ")
    public void say(JoinPoint joinPoint){
        SayOut annotation = joinPoint.getTarget().getClass().getAnnotation(SayOut.class);
        System.out.println(annotation.value());
    }

}
