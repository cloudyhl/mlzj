package com.mlzj.activity.service.impl;

import com.mlzj.activity.model.dto.UserAddDto;
import com.mlzj.activity.service.UserService;
import javax.annotation.Resource;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

/**
 * @author yhl
 * @date 2022/7/29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IdentityService identityService;


    @Override
    public void addUser(UserAddDto userAddDto) {
        User user = identityService.newUser(userAddDto.getUserId());
        user.setFirstName(userAddDto.getUserName());
        identityService.saveUser(user);
    }
}
