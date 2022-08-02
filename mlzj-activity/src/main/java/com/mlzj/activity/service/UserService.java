package com.mlzj.activity.service;

import com.mlzj.activity.model.dto.UserAddDto;

/**
 * @author yhl
 * @date 2022/7/29
 */
public interface UserService {

    /**
     * 新增用户信息
     * @param userAddDto 用户
     */
    void addUser(UserAddDto userAddDto);

}
