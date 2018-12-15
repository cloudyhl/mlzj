package com.mlzj.mlzjsecurity.service;

import com.mlzj.mlzjsecurity.entity.SysUser;

/**
 * @author yhl
 * @date 2018/12/11
 */
public interface UserService {
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 系统用户
     */
    SysUser getUserByName(String username);
}
