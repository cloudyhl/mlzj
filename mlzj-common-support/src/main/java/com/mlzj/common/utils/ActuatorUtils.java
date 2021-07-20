package com.mlzj.common.utils;


import com.mlzj.common.domain.Actuator;

/**
 * @author yhl
 * @date 2020/5/20
 */
public class ActuatorUtils {

    private ActuatorUtils(){}

    /**
     * 如果返回值是真则执行后面的操作
     * @param expression 表达式
     * @param actuator 执行器
     */
    public static void isTrue(boolean expression, Actuator actuator){
        if (expression){
            actuator.actuator();
        }
    }


    /**
     * 如果返回值是真则执行后面的操作
     * @param expression 表达式
     * @param actuator 执行器
     */
    public static void isFalse(boolean expression, Actuator actuator){
        if (!expression){
            actuator.actuator();
        }
    }

    /**
     * 根据表达式抉择执行的代码
     * @param expression 表达式
     * @param isTrue 为真
     * @param isFalse 为假
     */
    public static void orElse(boolean expression, Actuator isTrue, Actuator isFalse){
        if (expression){
            isTrue.actuator();
        } else {
            isFalse.actuator();
        }
    }

}
