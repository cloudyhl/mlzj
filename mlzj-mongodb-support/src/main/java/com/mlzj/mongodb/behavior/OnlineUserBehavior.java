package com.mlzj.mongodb.behavior;

/**
 * 登录的用户
 * @author yhl
 * @date 2019/5/24
 */
public interface OnlineUserBehavior<T> {

    /**
     * 获取用户姓名
     * @return 用户名
     */
    String getUserName();

    /**
     * 获取用户账号
     * @return 账号
     */
    String getAccount();

    /**
     * 附加信息
     * @return 返回附加信息
     */
    T getSupplement();
}
