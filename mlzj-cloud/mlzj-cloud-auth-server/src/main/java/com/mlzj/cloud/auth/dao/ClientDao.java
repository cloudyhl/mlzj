package com.mlzj.cloud.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mlzj.cloud.auth.model.SimpleClientDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhl
 * @date 2019/7/29
 */
@Mapper
public interface ClientDao extends BaseMapper<SimpleClientDetail> {
}
