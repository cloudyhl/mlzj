package com.mlzj.hbase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mlzj.hbase.dto.UserEntity;

public interface UserService extends IService<UserEntity> {

    void insertUser(UserEntity userEntity);

}
