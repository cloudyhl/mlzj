package com.mlzj.cloud.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.cloud.auth.dao.UserDao;
import com.mlzj.cloud.auth.model.SimpleUserDetail;
import com.mlzj.cloud.auth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author yhl
 * @date 2019/7/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, SimpleUserDetail> implements UserService {

    @Override
    public UserDetails loadUserByUsername(String userName) {
        QueryWrapper<SimpleUserDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        return super.getOne(queryWrapper);
    }
}
