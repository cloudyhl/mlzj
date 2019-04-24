package com.mlzj.dubbo.interfaces.service.user;

import com.mlzj.dubbo.interfaces.vo.User;

import java.util.List;

/**
 * 用户restApi
 * @author yhl
 * @date 2019/4/21
 */
public interface UserRestService {

    /**
     * 查询用户信息
     * @param id id
     * @return 用户集合
     */
    List<User> selectUsers(Integer id);


}
