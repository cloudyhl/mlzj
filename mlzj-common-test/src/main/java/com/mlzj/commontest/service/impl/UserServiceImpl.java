package com.mlzj.commontest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.commontest.dao.UserDao;
import com.mlzj.commontest.model.User;
import com.mlzj.commontest.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yhl
 * @date 2019/4/26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Override
    public void saveUser(User user) {
        super.save(user);
    }
}
