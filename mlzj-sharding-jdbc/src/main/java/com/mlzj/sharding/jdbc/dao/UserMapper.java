package com.mlzj.sharding.jdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.sharding.jdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
