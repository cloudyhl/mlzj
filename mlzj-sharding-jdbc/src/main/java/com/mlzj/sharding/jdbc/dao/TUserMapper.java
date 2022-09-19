package com.mlzj.sharding.jdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.mlzj.sharding.jdbc.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

}
