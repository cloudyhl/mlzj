package com.mlzj.hbase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.hbase.dto.UserEntity;
import com.mlzj.hbase.mapper.UserEntityMapper;
import com.mlzj.hbase.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity> implements UserService {

    @Resource
    private UserEntityMapper userEntityMapper;

    @Override
    public void insertUser(UserEntity userEntity) {
        userEntityMapper.insertUserEntity(userEntity);
    }
}
