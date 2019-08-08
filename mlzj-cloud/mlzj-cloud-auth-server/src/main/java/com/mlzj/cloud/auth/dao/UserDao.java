package com.mlzj.cloud.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.cloud.auth.model.SimpleUserDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhl
 * @date 2019/7/25
 */
@Mapper
public interface UserDao extends BaseMapper<SimpleUserDetail> {
}
