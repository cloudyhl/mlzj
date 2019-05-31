package com.mlzj.mongodb.behavior.aspect;

import com.mlzj.mongodb.behavior.BehaviorInterface;
import com.mlzj.mongodb.behavior.OnlineUserBehavior;
import com.mlzj.mongodb.behavior.SimpleUserBehavior;
import com.mlzj.mongodb.behavior.annotation.BehaviorLog;
import com.mlzj.mongodb.behavior.dao.BehaviorDao;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author yhl
 * @date 2019/5/24
 */
@Component
@Aspect
@ConditionalOnBean(BehaviorInterface.class)
public class BehaviorLogAspect {

    @Resource
    private BehaviorInterface behaviorInterface;

    @Resource
    private BehaviorDao behaviorDao;

    @Before(value = "@annotation(behaviorLog)")
    public void addBehaviorLog(JoinPoint point, BehaviorLog behaviorLog) throws NoSuchMethodException {
        Object target = point.getTarget();
        Signature signature = point.getSignature();
        Method method = target.getClass().getMethod(signature.getName());
        BehaviorLog annotation = method.getAnnotation(BehaviorLog.class);
        OnlineUserBehavior userBehavior = behaviorInterface.getUserBehavior();
        SimpleUserBehavior simpleUserBehavior = this.getSimpleUserBehavior(annotation, userBehavior);
        behaviorDao.save(simpleUserBehavior);
    }

    /**
     * 获取操作行为记录信息
     * @param behaviorLog 行为记录注解
     * @param onlineUserBehavior 用户信息
     * @return 操作记录信息
     */
    private SimpleUserBehavior getSimpleUserBehavior(BehaviorLog behaviorLog,OnlineUserBehavior onlineUserBehavior) {
        SimpleUserBehavior simpleUserBehavior = new SimpleUserBehavior();
        simpleUserBehavior.setAccount(onlineUserBehavior.getAccount());
        simpleUserBehavior.setUserName(onlineUserBehavior.getUserName());
        simpleUserBehavior.setSupplement(onlineUserBehavior.getSupplement());
        simpleUserBehavior.setAppName(behaviorLog.appName());
        simpleUserBehavior.setValue(behaviorLog.value());
        simpleUserBehavior.setActionName(behaviorLog.actionName());
        simpleUserBehavior.setDate(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return simpleUserBehavior;

    }

}
