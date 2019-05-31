package com.mlzj.commontest.service;

import com.mlzj.commontest.model.User;

/**
 * @author yhl
 * @date 2019/4/26
 */
public interface UserService {

    /**
     * 保存用户
     * @param user 用户
     */
    void saveUser(User user);
}
