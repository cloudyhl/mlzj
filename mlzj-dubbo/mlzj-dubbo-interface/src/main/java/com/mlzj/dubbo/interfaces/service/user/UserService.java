package com.mlzj.dubbo.interfaces.service.user;

import com.mlzj.dubbo.interfaces.vo.User;

import java.util.List;

/**
 * @author yhl
 * @date 2019/4/16
 */
public interface UserService {

    /**
     * 查询所有用户
     * @return 用户信息
     */
    List<User> selectUser();
}
