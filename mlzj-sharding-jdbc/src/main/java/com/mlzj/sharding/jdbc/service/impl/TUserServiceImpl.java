package com.mlzj.sharding.jdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.mlzj.sharding.jdbc.dao.TUserMapper;
import com.mlzj.sharding.jdbc.entity.TUser;
import com.mlzj.sharding.jdbc.service.TUserService;
import org.springframework.stereotype.Service;

/**
 * @author yhl
 * @date 2022/9/12
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements TUserService {



}
