package com.mlzj.mongodb.behavior;


/**
 *
 * @author yhl
 * @date 2019/5/24
 */
public interface BehaviorInterface {

    /**
     * 获取登陆用户
     * @return 登录的用户
     */
    OnlineUserBehavior getUserBehavior();
}
