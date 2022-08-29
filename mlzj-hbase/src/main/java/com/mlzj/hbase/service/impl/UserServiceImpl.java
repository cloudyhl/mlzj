package com.mlzj.hbase.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mlzj.hbase.dto.UserEntity;
import com.mlzj.hbase.mapper.UserEntityMapper;
import com.mlzj.hbase.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity> implements UserService {

}
